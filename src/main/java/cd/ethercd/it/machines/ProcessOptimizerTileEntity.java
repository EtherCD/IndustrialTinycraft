package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.item.ItemStack;

public class ProcessOptimizerTileEntity extends ComplexMachineTileEntity {

    public ProcessOptimizerTileEntity() {
        super(8000, 2, 3000);
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 3, ITcRecipes.processs_optimizer_ic2_plug);
    }

    public void consume() {
        inputSlot.get(0).setCount(inputSlot.get(0).getCount()-1);
        inputSlot.get(1).setCount(inputSlot.get(1).getCount()-1);
        inputSlot.get(2).setCount(inputSlot.get(1).getCount()-1);
    }

    public boolean canOperate() {
        if (this.inputSlot.get(0).isEmpty() || this.inputSlot.get(1).isEmpty()) {
            return false;
        }
        ItemStack output = ITcRecipes.processs_optimizer.getResult(this.inputSlot.get(0), this.inputSlot.get(1), this.inputSlot.get(2));
        return !output.isEmpty() && this.outputSlot.canAdd(output);
    }
}
