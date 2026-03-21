package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.IUpgradeItem;
import ic2.api.upgrade.UpgradableProperty;
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

public abstract class ComplexMachineTileEntity extends TileEntityElectricMachine implements IHasGui, IGuiValueProvider, IUpgradableBlock {
    protected final int idleEU = 0;
    protected int activeEU = 32;
    protected int maxProgress;

    protected InvSlotProcessable<IRecipeInput, Collection<ItemStack>, ItemStack> inputSlot;
    protected final InvSlotOutput outputSlot;
    protected final InvSlotUpgrade upgradeSlot;

    @GuiSynced
    public int progress;

    public ComplexMachineTileEntity(int maxEnergy, int tier, int maxProgress) {
        super(maxEnergy, tier);
        this.maxProgress = maxProgress;
        this.progress = 0;
        this.outputSlot = new InvSlotOutput(this, "output", 1);
        this.upgradeSlot  = new InvSlotUpgrade(this, "upgrade", 4);
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

        double accelerate = 1.0;
        double energy = 1.0;

        Iterator<ItemStack> it = this.upgradeSlot.iterator();
        while (it.hasNext()) {
            ItemStack stack = it.next();
            if (!StackUtil.isEmpty(stack) && stack.getItem() instanceof IUpgradeItem) {
                if (stack.getItem() instanceof IProcessingUpgrade) {
                    IProcessingUpgrade upgrade = (IProcessingUpgrade) stack.getItem();
                    accelerate /= Math.pow(upgrade.getProcessTimeMultiplier(stack, this), StackUtil.getSize(stack));
                    energy *= Math.pow(upgrade.getEnergyDemandMultiplier(stack, this), StackUtil.getSize(stack));
                }
            }
        }

        if (this.progress >= this.maxProgress) {
            while (this.progress >= this.maxProgress && canOperate) {
                this.operate();

                this.consume();

                this.progress -= this.maxProgress;
                canOperate = this.canOperate();
                needsInvUpdate = true;
            }
        }

        boolean active = false;

        if (this.canRun()) {
            if (canOperate && this.energy.useEnergy((double) this.activeEU * energy)) {
                this.progress += (int) (10 * accelerate);
                active = true;
            } else {
                this.progress = 0;
            }
        } else {
            this.progress = 0;
        }

        it = this.upgradeSlot.iterator();
        while (it.hasNext()) {
            ItemStack stack = it.next();
            if (!StackUtil.isEmpty(stack) && stack.getItem() instanceof IUpgradeItem) {
                needsInvUpdate |= ((IUpgradeItem) stack.getItem()).onTick(stack, this);
            }
        }

        this.setActive(active);

        if (needsInvUpdate) {
            this.markDirty();
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.markDirty();
        super.setInventorySlotContents(index, stack);
    }

    void consume() {}

    boolean canOperate() {
        return false;
    }

    public void operate() {
        this.canOperate();
        final MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack> output = (MachineRecipeResult<IRecipeInput, Collection<ItemStack>, ItemStack>)this.inputSlot.process();
        this.processUpgrades((Collection<ItemStack>)output.getOutput());
        this.outputSlot.add((Collection<ItemStack>)output.getOutput());
    }

    protected void processUpgrades(final Collection<ItemStack> output) {
        for (final ItemStack stack : this.upgradeSlot) {
            if (stack != null && stack.getItem() instanceof IUpgradeItem) {
                ((IUpgradeItem)stack.getItem()).onProcessEnd(stack, (IUpgradableBlock)this, (Collection<ItemStack>)output);
            }
        }
    }

    public String getSound() {
        return "Machines/MaceratorOp.ogg";
    }
}