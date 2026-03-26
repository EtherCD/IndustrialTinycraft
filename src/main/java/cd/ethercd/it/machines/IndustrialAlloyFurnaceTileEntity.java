package cd.ethercd.it.machines;

import ic2.api.recipe.Recipes;

public class IndustrialAlloyFurnaceTileEntity extends SimpleMachineTileEntity {
    public IndustrialAlloyFurnaceTileEntity() {
        super(5, Recipes.blastfurnace, 0, 640, 4000000, 2);
        this.maxProgress = 2000;
        this.setActive(false);
    }

    @Override
    public boolean canOperate() {
        return super.canOperate();
    }
}
