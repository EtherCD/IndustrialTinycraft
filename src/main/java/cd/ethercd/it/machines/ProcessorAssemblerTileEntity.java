package cd.ethercd.it.machines;

import cd.ethercd.it.ITcRecipes;
import ic2.api.recipe.IRecipeInput;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotUpgrade;
import ic2.core.network.GuiSynced;
import net.minecraft.item.ItemStack;
import java.util.*;

public class ProcessorAssemblerTileEntity extends SimpleMachineTileEntity {
    public ProcessorAssemblerTileEntity() {
        super(2, ITcRecipes.processor_assembler, 1, 200);
    }

    public String getSound() {
        return "Machines/MaceratorOp.ogg";
    }
}
