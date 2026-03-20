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
import ic2.core.block.machine.tileentity.TileEntityExtractor;
import ic2.core.gui.dynamic.DynamicContainer;
import ic2.core.gui.dynamic.DynamicGui;
import ic2.core.gui.dynamic.GuiParser;
import ic2.core.gui.dynamic.IGuiValueProvider;
import ic2.core.network.GuiSynced;
import ic2.core.util.StackUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
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

    protected int inventorySize = 0;

    private int initialMaxEnergy = 8000;

    @GuiSynced
    public int progress;

    public SimpleMachineTileEntity(int tier, IMachineRecipeManager<IRecipeInput, Collection<ItemStack>, ItemStack> recipeSet, int idleEU, int activeEU) {
        super(8000, tier);
        this.maxProgress = 1000;
        this.progress = 0;
        this.idleEU = idleEU;
        this.activeEU = activeEU;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 1, recipeSet);
        this.outputSlot = new InvSlotOutput(this, "output", 1);
        this.upgradeSlot  = new InvSlotUpgrade(this, "upgrade", 2);
    }

    @Override
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.progress = nbt.getInteger("progress");
    }

    @Override
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
        Iterator<ItemStack> var4 = this.upgradeSlot.iterator();
        double accelerate = 1;
        double energy = 1;
        int additionalEnergyCapacity = 0;
        while(var4.hasNext()) {
            ItemStack stack = (ItemStack)var4.next();
            if (!StackUtil.isEmpty(stack) && stack.getItem() instanceof IUpgradeItem) {
                needsInvUpdate |= ((IUpgradeItem)stack.getItem()).onTick(stack, this);
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
        if (this.canRun()) {
            if (canOperate && this.energy.useEnergy((double)this.activeEU * energy)) {
                this.progress += (int) (10 * accelerate);
            } else {
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }
        this.setActive(true);
        if (needsInvUpdate) {
            this.markDirty();
        }
    }

    public boolean canOperate() {
        if (this.inputSlot.isEmpty()) {
            return false;
        }
        final MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> output = (MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack>)this.inputSlot.process();
        return output != null && this.outputSlot.canAdd((Collection<ItemStack>)output.getOutput());
    }

    public void operate() {
        this.canOperate();
        final MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> output = (MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack>)this.inputSlot.process();
        this.processUpgrades((Collection<ItemStack>)output.getOutput());
        this.outputSlot.add((Collection<ItemStack>)output.getOutput());
        this.inputSlot.consume((MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack>)output);
    }

    protected void processUpgrades(final Collection<ItemStack> output) {
        for (final ItemStack stack : this.upgradeSlot) {
            if (stack != null && stack.getItem() instanceof IUpgradeItem) {
                ((IUpgradeItem)stack.getItem()).onProcessEnd(stack, (IUpgradableBlock)this, (Collection<ItemStack>)output);
            }
        }
    }
}
