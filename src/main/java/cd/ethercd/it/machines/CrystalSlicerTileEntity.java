package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;

public class CrystalSlicerTileEntity extends SimpleMachineTileEntity {
    public CrystalSlicerTileEntity() {
        super(4, ITcRecipes.crystal_slicer, 0, 200, 100000);
        this.maxProgress = 2000;
    }
}
