package cd.ethercd.it.storages;

import cd.ethercd.it.utils.gui.ContainerElectricStorageBlock;
import cd.ethercd.it.utils.gui.GuiElectricStorageBlock;
import ic2.core.ContainerBase;
import ic2.core.block.invslot.InvSlot;
import ic2.core.block.invslot.InvSlotCharge;
import ic2.core.block.wiring.ContainerElectricBlock;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.block.wiring.TileEntityElectricMFSU;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EnergyStorageTileEntity extends TileEntityElectricBlock {
    public InvSlotCharge chargeSlot2;

    public EnergyStorageTileEntity(int tier, int maxEnergy) {
        super(tier, (int) (32 * Math.pow(4, tier - 1)), maxEnergy);
        this.chargeSlot2 = new InvSlotCharge(this, tier);
        this.energy.addManagedSlot(this.chargeSlot2);
    }

    @Override
    public ContainerBase<? extends TileEntityElectricBlock> getGuiContainer(EntityPlayer player) {
        return new ContainerElectricStorageBlock(player, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer player, boolean isAdmin) {
        return new GuiElectricStorageBlock(new ContainerElectricStorageBlock(player, this));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        return nbt;
    }
}
