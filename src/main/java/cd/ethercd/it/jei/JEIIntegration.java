package cd.ethercd.it.jei;

import cd.ethercd.it.ITcMachines;
import cd.ethercd.it.ITcRecipes;
import com.google.common.collect.Lists;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipe;
import ic2.core.recipe.BasicMachineRecipeManager;
import ic2.jeiIntegration.recipe.machine.IRecipeWrapperGenerator;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIIntegration implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new CrystalGrowerCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(ITcRecipes.crystal_grower.getRecipes()), CrystalGrowerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, recipe -> new CrystalGrowerWrapper(recipe), CrystalGrowerCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ITcMachines.crystal_grower.getDummyTe().getBlockType()), CrystalGrowerCategory.UID);
    }
}
