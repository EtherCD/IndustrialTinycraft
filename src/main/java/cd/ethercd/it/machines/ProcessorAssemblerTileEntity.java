package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;

public class ProcessorAssemblerTileEntity  extends ComplexMachineTileEntity {
    public ProcessorAssemblerTileEntity() {
        super(2000000, 4, 1500);
        this.activeEU = 600;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 2, ITcRecipes.processor_assembler_ic2_plug);
    }

    @Override
    public void consume() {
        ItemStack input1 = inputSlot.get(0);
        ItemStack input2 = inputSlot.get(1);
        int[] ingredientsConsume = ITcRecipes.processor_assembler.getIngirientsConsume(input1, input2);

        if (!input1.isEmpty()) {
            input1.shrink(ingredientsConsume[0]);
            if (input1.getCount() <= 0) {
                inputSlot.put(0, ItemStack.EMPTY);
            }
        }

        if (!input2.isEmpty()) {
            input2.shrink(ingredientsConsume[1]);
            if (input2.getCount() <= 0) {
                inputSlot.put(1, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public boolean canOperate() {
        if (this.inputSlot.get(0).isEmpty() && this.inputSlot.get(1).isEmpty()) {
            return false;
        }
        ItemStack output = ITcRecipes.processor_assembler.getResult(this.inputSlot.get(0), this.inputSlot.get(1));
        return !output.isEmpty() && this.outputSlot.canAdd(output);
    }
}
