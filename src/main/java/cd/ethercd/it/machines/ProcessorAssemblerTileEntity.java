package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.item.ItemStack;

public class ProcessorAssemblerTileEntity  extends ComplexMachineTileEntity {
    public ProcessorAssemblerTileEntity() {
        super(5000000, 4, 1500);
        this.activeEU = 600;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 2, ITcRecipes.processor_assembler_ic2_plug);
    }

    public void consume() {
        inputSlot.get(0).setCount(inputSlot.get(0).getCount()-1);
        inputSlot.get(1).setCount(inputSlot.get(1).getCount()-1);
    }

    public boolean canOperate() {
        if (this.inputSlot.get(0).isEmpty() || this.inputSlot.get(1).isEmpty()) {
            return false;
        }
        ItemStack output = ITcRecipes.processor_assembler.getResult(this.inputSlot.get(0), this.inputSlot.get(1));
        return !output.isEmpty() && this.outputSlot.canAdd(output);
    }
}
