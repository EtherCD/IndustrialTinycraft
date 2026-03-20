package cd.ethercd.it.jei.machines;

import cd.ethercd.it.ITcMachines;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.IndustrialTinyCraft;
import cd.ethercd.it.utils.ProcessorAssemblerRecipeManager;
import ic2.jeiIntegration.SlotPosition;
import ic2.jeiIntegration.recipe.machine.IORecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class ProcessOptimizerCategory extends IORecipeCategory<ProcessorAssemblerRecipeManager> implements IDrawable {
    private final IDrawableStatic bg;
    public static final String UID = IndustrialTinyCraft.MODID + ".process_optimizer";

    public ProcessOptimizerCategory(IGuiHelper h) {
        super(ITcMachines.crystal_grower, ITcRecipes.processor_assembler);
        bg = h.createDrawable(new ResourceLocation(IndustrialTinyCraft.MODID + ":textures/gui/process_optimizer.png"), 0, 0, 103, 18, 103, 18);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true,  0, 0);
        recipeLayout.getItemStacks().init(1, true,  18, 0);
        recipeLayout.getItemStacks().init(2, true,  36, 0);
        recipeLayout.getItemStacks().init(3, false,  85, 0);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return "Processor Assembler";
    }

    @Override
    public String getModName() {
        return IndustrialTinyCraft.NAME;
    }

    @Override
    protected List<SlotPosition> getInputSlotPos() {
        return Collections.emptyList();
    }

    @Override
    protected List<SlotPosition> getOutputSlotPos() {
        return Collections.emptyList();
    }

    @Override
    public int getWidth() {
        return 62;
    }

    @Override
    public int getHeight() {
        return 26;
    }

    @Override
    public void draw(Minecraft minecraft, int xOffset, int yOffset) {}

    @Override
    public IDrawable getBackground() {
        return bg;
    }
}

