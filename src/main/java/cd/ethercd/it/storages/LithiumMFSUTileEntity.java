package cd.ethercd.it.storages;

import cd.ethercd.it.utils.gui.ContainerElectricStorageBlock;
import ic2.core.ContainerBase;
import ic2.core.block.wiring.TileEntityElectricBlock;
import net.minecraft.entity.player.EntityPlayer;

public class LithiumMFSUTileEntity extends EnergyStorageTileEntity {
    public LithiumMFSUTileEntity() {
        super(5, 112000000);
    }

    @Override
    public ContainerBase<? extends TileEntityElectricBlock> getGuiContainer(EntityPlayer player) {
        return new ContainerElectricStorageBlock(player, this);
    }
}
