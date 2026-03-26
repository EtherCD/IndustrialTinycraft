package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;

public class CrystalGrowerTileEntity extends SimpleMachineTileEntity {
    public CrystalGrowerTileEntity() {
        super(1, ITcRecipes.crystal_grower, 0, 16, 8000);
        this.maxProgress = 10000;
    }
}
