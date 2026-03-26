package cd.ethercd.it.jei.machines;

import cd.ethercd.it.ITcMachine;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.IndustrialTinyCraft;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.jeiIntegration.SlotPosition;
import ic2.jeiIntegration.recipe.machine.IORecipeCategory;
import mcp.MethodsReturnNonnullByDefault;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class CrystalGrowerCategory extends IORecipeCategory<IBasicMachineRecipeManager> implements IDrawable {
    private final IDrawableStatic bg;
    public static final String UID = IndustrialTinyCraft.MODID + ".crystal_grower";

    public CrystalGrowerCategory(IGuiHelper h) {
        super(ITcMachine.crystal_grower, ITcRecipes.crystal_grower);
        bg = h.createDrawable(new ResourceLocation(IndustrialTinyCraft.MODID + ":textures/gui/crystal_grower.png"), 0, 0, 82, 26, 82, 26);
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true,  0, 4);
        recipeLayout.getItemStacks().init(1, false,  60, 4);
        recipeLayout.getItemStacks().set(ingredients);
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getUid() {
        return UID;
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getTitle() {
        return "Crystal Grower";
    }

    @Override
    @MethodsReturnNonnullByDefault
    public String getModName() {
        return IndustrialTinyCraft.NAME;
    }

    @Override
    protected List<SlotPosition> getInputSlotPos() {
        List<SlotPosition> array = new java.util.ArrayList<>();
        array.add(0, new SlotPosition(0, 4));
        return array;
    }

    @Override
    protected List<SlotPosition> getOutputSlotPos() {
        List<SlotPosition> array = new java.util.ArrayList<>();
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
    @MethodsReturnNonnullByDefault
    @ParametersAreNonnullByDefault
    public void draw(Minecraft minecraft, int xOffset, int yOffset) {}

    @Override
    @MethodsReturnNonnullByDefault
    public IDrawable getBackground() {
        return bg;
    }
}

