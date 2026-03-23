package cd.ethercd.it.utils.gui;

import cd.ethercd.it.storages.EnergyStorageTileEntity;
import ic2.core.ContainerFullInv;
import ic2.core.slot.ArmorSlot;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class ContainerElectricStorageBlock extends ContainerFullInv<EnergyStorageTileEntity> {
    public ContainerElectricStorageBlock(EntityPlayer player, EnergyStorageTileEntity base) {
        super(player, base, 196);
        for(int col = 0; col < ArmorSlot.getCount(); ++col) {
            this.addSlotToContainer(new SlotArmor(player.inventory, ArmorSlot.get(col), 8 + col * 18, 84));
        }

        this.addSlotToContainer(new SlotInvSlot(base.chargeSlot, 0, 56, 17));
        this.addSlotToContainer(new SlotInvSlot(base.chargeSlot2, 0, 38, 17));
        this.addSlotToContainer(new SlotInvSlot(base.dischargeSlot, 0, 56, 53));
    }

    public List<String> getNetworkedFields() {
        List<String> ret = super.getNetworkedFields();
        ret.add("redstoneMode");
        return ret;
    }
}



