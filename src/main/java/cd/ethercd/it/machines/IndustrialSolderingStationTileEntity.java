package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.item.ItemStack;

import java.util.Collections;

public class IndustrialSolderingStationTileEntity extends ComplexMachineTileEntity {

    public IndustrialSolderingStationTileEntity() {
        super(100000, 4, 3000);
        this.activeEU = 600;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 3, ITcRecipes.industrial_soldering_station.ic2_input_plug);
    }

    @Override
    public void consume() {
        ItemStack input1 = inputSlot.get(0);
        ItemStack input2 = inputSlot.get(1);
        ItemStack input3 = inputSlot.get(2);
        int[] ingredientsConsume = ITcRecipes.industrial_soldering_station.getIngirientsConsume(input1, input2, input3);

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

        if (!input3.isEmpty()) {
            input3.shrink(ingredientsConsume[2]);
            if (input3.getCount() <= 0) {
                inputSlot.put(2, ItemStack.EMPTY);
            }
        }
    }

    public void operate() {
        ItemStack output = ITcRecipes.industrial_soldering_station.getResult(this.inputSlot.get(0), this.inputSlot.get(1), this.inputSlot.get(2));
        this.processUpgrades(Collections.singletonList(output));
        this.outputSlot.add(output);
    }

    @Override
    public boolean canOperate() {
        if (this.inputSlot.get(0).isEmpty() && this.inputSlot.get(1).isEmpty() && this.inputSlot.get(2).isEmpty()) {
            return false;
        }
        ItemStack output = ITcRecipes.industrial_soldering_station.getResult(this.inputSlot.get(0), this.inputSlot.get(1), this.inputSlot.get(2));
        return !output.isEmpty() && this.outputSlot.canAdd(output);
    }
}
