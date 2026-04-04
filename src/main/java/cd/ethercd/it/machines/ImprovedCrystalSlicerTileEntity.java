package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;

public class ImprovedCrystalSlicerTileEntity extends SimpleMachineTileEntity {
    public ImprovedCrystalSlicerTileEntity() {
        super(5, ITcRecipes.crystal_slicer, 0, 600, 5000000);
        this.maxProgress = 600;
    }
}
