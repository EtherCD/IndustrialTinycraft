package cd.ethercd.it.jei;

import cd.ethercd.it.ITcMachine;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.jei.machines.CrystalGrowerCategory;
import cd.ethercd.it.jei.machines.CrystalGrowerWrapper;
import cd.ethercd.it.jei.machines.ProcessOptimizerCategory;
import cd.ethercd.it.jei.machines.LithographyUnitCategory;
import com.google.common.collect.Lists;
import ic2.api.recipe.MachineRecipe;
import ic2.core.block.ITeBlock;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;

@JEIPlugin
public class JEIIntegration implements IModPlugin {
    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new CrystalGrowerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new LithographyUnitCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ProcessOptimizerCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(ITcRecipes.crystal_grower.getRecipes()), CrystalGrowerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, CrystalGrowerWrapper::new, CrystalGrowerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.crystal_grower, CrystalGrowerCategory.UID);
        registry.addRecipes(ITcRecipes.lithography_unit.getRecipes(), LithographyUnitCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.lithography_unit, LithographyUnitCategory.UID);
        registry.addRecipes(ITcRecipes.processs_optimizer.getRecipes(), ProcessOptimizerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.process_optimizer, ProcessOptimizerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.industrial_alloy_furnace, "blast_furnace");
    }

    private void addRecipeCatalyst(IModRegistry registry, ITeBlock block, String uid) {
        registry.addRecipeCatalyst(block.getDummyTe().getBlockType().getItemStack(block), uid);
    }
}
