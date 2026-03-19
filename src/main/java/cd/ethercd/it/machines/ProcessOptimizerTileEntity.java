package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;

public class ProcessOptimizerTileEntity extends SimpleMachineTileEntity {
    public ProcessOptimizerTileEntity() {
        super(2, ITcRecipes.processor_assembler_ic2_plug, 1, 200);
    }

    public String getSound() {
        return "Machines/MaceratorOp.ogg";
    }
}
