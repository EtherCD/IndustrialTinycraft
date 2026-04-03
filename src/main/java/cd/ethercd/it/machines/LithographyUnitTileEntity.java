package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotProcessableGeneric;
import net.minecraft.item.ItemStack;

import java.util.Collections;

public class LithographyUnitTileEntity extends ComplexMachineTileEntity {
    private final InvSlotProcessable maskInput;

    public LithographyUnitTileEntity() {
        super(2000000, 4, 1500);
        this.activeEU = 600;
        this.inputSlot = new InvSlotProcessableGeneric(this, "input", 1, ITcRecipes.lithography_unit.ic2_input_plug);
        this.maskInput = new InvSlotProcessableGeneric(this, "mask_input", 1, ITcRecipes.lithography_unit.ic2_mask_input_plug);
    }

    @Override
    public void consume() {
        ItemStack mask = this.maskInput.get(0);
        ItemStack plate = this.inputSlot.get(0);
        int plateConsume = ITcRecipes.lithography_unit.getPlateConsume(mask);

        plate.shrink(plateConsume);
        if (plate.getCount() <= 0) {
            inputSlot.put(0, ItemStack.EMPTY);
        }
    }

    @Override
    public boolean canOperate() {
        if (this.inputSlot.get(0).isEmpty() && this.maskInput.get(0).isEmpty()) {
            return false;
        }
        ItemStack output = ITcRecipes.lithography_unit.getResult(this.maskInput.get(0), this.inputSlot.get(0));
        return !output.isEmpty() && this.outputSlot.canAdd(output);
    }

    @Override
    protected void operate() {
        ItemStack output = ITcRecipes.lithography_unit.getResult(this.maskInput.get(0), this.inputSlot.get(0));
        this.processUpgrades(Collections.singletonList(output));
        this.outputSlot.add(output);
    }
}
