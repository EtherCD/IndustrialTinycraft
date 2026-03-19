package cd.ethercd.it.jei.machines;

import cd.ethercd.it.ITcMachines;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.IndustrialTinyCraft;
import ic2.api.recipe.IBasicMachineRecipeManager;
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

public class CrystalGrowerCategory extends IORecipeCategory<IBasicMachineRecipeManager> implements IDrawable {
    private final IDrawableStatic bg;
    public static final String UID = IndustrialTinyCraft.MODID + ".crystal_grower";

    public CrystalGrowerCategory(IGuiHelper h) {
        super(ITcMachines.crystal_grower, ITcRecipes.crystal_grower);
        bg = h.createDrawable(new ResourceLocation(IndustrialTinyCraft.MODID + ":textures/gui/crystal_grower.png"), 0, 0, 82, 26, 82, 26);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true,  0, 4);
        recipeLayout.getItemStacks().init(1, false,  59, 4);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    public String getUid() {
        return UID;
    }

    @Override
    public String getTitle() {
        return "Crystal Grower";
    }

    @Override
    public String getModName() {
        return IndustrialTinyCraft.NAME;
    }

    @Override
    protected List<SlotPosition> getInputSlotPos() {
        List<SlotPosition> array = Collections.emptyList();
        array.add(0, new SlotPosition(0, 4));
        return array;
    }

    @Override
    protected List<SlotPosition> getOutputSlotPos() {
        List<SlotPosition> array = Collections.emptyList();
        array.add(0, new SlotPosition(56, 0));
        return array;
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

