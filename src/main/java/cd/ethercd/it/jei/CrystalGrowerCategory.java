package cd.ethercd.it.jei;

import cd.ethercd.it.ITcMachines;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.IndustrialTinyCraft;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.core.block.ITeBlock;
import ic2.jeiIntegration.SlotPosition;
import ic2.jeiIntegration.recipe.machine.IORecipeCategory;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class CrystalGrowerCategory extends IORecipeCategory<IBasicMachineRecipeManager> implements IDrawable {
    private final IDrawableStatic bg;
    public static final String UID = IndustrialTinyCraft.MODID + ":crystal_grower_crafts";

    public CrystalGrowerCategory(IGuiHelper h) {
        super(ITcMachines.crystal_grower, ITcRecipes.crystal_grower);
        bg = h.createDrawable(new ResourceLocation(IndustrialTinyCraft.MODID, "textures/gui/crystal_grower.png"), 0, 0, 62, 26); // Объявление background'а.
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

