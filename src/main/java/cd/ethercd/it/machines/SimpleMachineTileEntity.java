package cd.ethercd.it.machines;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.upgrade.*;
import ic2.core.ContainerBase;
import ic2.core.IHasGui;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import ic2.core.block.invslot.InvSlotUpgrade;
import ic2.core.block.machine.tileentity.TileEntityElectricMachine;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.gui.dynamic.IGuiValueProvider;
import ic2.core.network.GuiSynced;
import ic2.core.util.StackUtil;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAir;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;

// Basics from https://github.com/Artic030-new/IC2AddonTutorial-1.12.2/blob/master/src/main/java/ru/artic030/mod02/machines/TileEntityTestMachine.java
// Thanks to Artic030

public class SimpleMachineTileEntity extends TileEntityElectricMachine implements IHasGui, IGuiValueProvider, IUpgradableBlock {
    protected final int idleEU;
    protected final int activeEU;
    protected int maxProgress;

    public final InvSlotProcessable<IRecipeInput, Collection<ItemStack>, ItemStack> inputSlot;
    public final InvSlotOutput outputSlot;
    public final InvSlotUpgrade upgradeSlot;

    private final int initialMaxEnergy;

    @GuiSynced
    public int progress;

    public SimpleMachineTileEntity(int tier, IMachineRecipeManager<IRecipeInput, Collection<ItemStack>, ItemStack> recipeSet, int idleEU, int activeEU, int maxEnergy) {
        super(maxEnergy, tier);
        this.initialMaxEnergy = maxEnergy;
        this.maxProgress = 1000;
        this.progress = 0;
        this.idleEU = idleEU;
        this.activeEU = activeEU;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 1, recipeSet);
        this.outputSlot = new InvSlotOutput(this, "output", 1);
        this.upgradeSlot  = new InvSlotUpgrade(this, "upgrade", 4);
    }

    public SimpleMachineTileEntity(int tier, IMachineRecipeManager<IRecipeInput, Collection<ItemStack>, ItemStack> recipeSet, int idleEU, int activeEU, int maxEnergy, int outputSlots) {
        super(maxEnergy, tier);
        this.initialMaxEnergy = maxEnergy;
        this.maxProgress = 1000;
        this.progress = 0;
        this.idleEU = idleEU;
        this.activeEU = activeEU;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 1, recipeSet);
        this.outputSlot = new InvSlotOutput(this, "output", outputSlots);
        this.upgradeSlot  = new InvSlotUpgrade(this, "upgrade", 4);
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progress = nbt.getInteger("progress");
    }

    @Override
    @MethodsReturnNonnullByDefault
    public NBTTagCompound writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("progress", this.progress);
        return nbt;
    }

    protected void onUnloaded() {
        super.onUnloaded();
    }

    protected boolean canRun() {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer p, boolean arg1) {
        return DynamicGui.create(this, p, GuiParser.parse(this.teBlock));
    }

    @Override
    public ContainerBase<? extends TileEntityElectricMachine> getGuiContainer(EntityPlayer p) {
        return DynamicContainer.create(this, p, GuiParser.parse(this.teBlock));
    }

    @Override
    public void onGuiClosed(EntityPlayer arg0) {}

    @Override
    public double getGuiValue(String arg0) {
        return (double)(1000 * this.progress / this.maxProgress) / 1000.0D;
    }

    @Override
    public double getEnergy() {
        return this.energy.getEnergy();
    }

    @Override
    public boolean useEnergy(double amount) {
        return this.energy.useEnergy(amount);
    }

    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing, UpgradableProperty.Processing, UpgradableProperty.Transformer);
    }

    protected void updateEntityServer() {
        super.updateEntityServer();
        boolean needsInvUpdate = false;
        boolean canOperate = this.canOperate();
        Iterator<ItemStack> it = this.upgradeSlot.iterator();
        double accelerate = 1;
        double energy = 1;
        int additionalEnergyCapacity = 0;
        while(it.hasNext()) {
            ItemStack stack = it.next();
            if (!StackUtil.isEmpty(stack) && stack.getItem() instanceof IUpgradeItem) {
                if (stack.getItem() instanceof IProcessingUpgrade) {
                    IProcessingUpgrade upgrade = (IProcessingUpgrade) stack.getItem();
                    accelerate /= Math.pow(upgrade.getProcessTimeMultiplier(stack, this), StackUtil.getSize(stack));
                    energy *= Math.pow(upgrade.getEnergyDemandMultiplier(stack, this), StackUtil.getSize(stack));
                }
                if (stack.getItem() instanceof IEnergyStorageUpgrade) {
                    additionalEnergyCapacity += ((IEnergyStorageUpgrade) stack.getItem()).getExtraEnergyStorage(stack, this);
                }
            }
        }

        this.energy.setCapacity(this.initialMaxEnergy + additionalEnergyCapacity);

        if (this.progress >= this.maxProgress) {
            while(true) {
                if (this.progress < this.maxProgress || !canOperate) {
                    needsInvUpdate = true;
                    break;
                }
                this.operate();
                this.progress -= this.maxProgress;
                canOperate = this.canOperate();
            }
        }
        boolean active = false;

        if (this.canRun()) {
            if (canOperate && this.energy.useEnergy((double)this.activeEU * energy)) {
                this.progress += (int) (10 * accelerate);
                active = true;
            } else {
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }

        this.setActive(active);

        it = this.upgradeSlot.iterator();
        while (it.hasNext()) {
            ItemStack stack = it.next();
            if (!StackUtil.isEmpty(stack) && stack.getCount() > 0) {
                Item item =  stack.getItem();
                if (!(item instanceof ItemAir) && item instanceof IUpgradeItem) {
                    needsInvUpdate |= ((IUpgradeItem) item).onTick(stack, this);
                }
            }
        }

        if (needsInvUpdate) {
            this.markDirty();
        }
    }

    public boolean canOperate() {
        if (this.inputSlot.isEmpty()) {
            return false;
        }
        final MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> output = this.inputSlot.process();
        return output != null && this.outputSlot.canAdd(output.getOutput());
    }

    public void operate() {
        this.canOperate();
        final MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> output = this.inputSlot.process();
        this.processUpgrades(output.getOutput());
        this.outputSlot.add(output.getOutput());
        this.inputSlot.consume(output);
    }

    protected void processUpgrades(final Collection<ItemStack> output) {
        for (final ItemStack stack : this.upgradeSlot) {
            if (stack != null && stack.getItem() instanceof IUpgradeItem) {
                ((IUpgradeItem)stack.getItem()).onProcessEnd(stack, this, output);
            }
        }
    }
}
