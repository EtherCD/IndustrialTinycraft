package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.api.recipe.Recipes;
import ic2.core.IC2;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.machine.tileentity.TileEntityBlastFurnace;
import ic2.core.network.NetworkManager;

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

    public String getSound() {
        return "Machines/MaceratorOp.ogg";
    }
}
