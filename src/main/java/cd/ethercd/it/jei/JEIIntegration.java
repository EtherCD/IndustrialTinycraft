package cd.ethercd.it.jei;

import cd.ethercd.it.ITcMachine;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.jei.machines.*;
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
        registry.addRecipeCategories(new CrystalSlicerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImprovedCrystalSlicerCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new LithographyUnitCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new ImprovedLithographyUnitCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategories(new IndustrialSolderingStationCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(ITcRecipes.crystal_grower.getRecipes()), CrystalGrowerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, BasicRecipeWrapper::new, CrystalGrowerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.crystal_grower, CrystalGrowerCategory.UID);

        registry.addRecipes(Lists.newArrayList(ITcRecipes.crystal_slicer.getRecipes()), CrystalSlicerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, BasicRecipeWrapper::new, CrystalSlicerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.crystal_slicer, CrystalSlicerCategory.UID);

        registry.addRecipes(Lists.newArrayList(ITcRecipes.improved_crystal_slicer.getRecipes()), ImprovedCrystalSlicerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, BasicRecipeWrapper::new, ImprovedCrystalSlicerCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.improved_crystal_slicer, ImprovedCrystalSlicerCategory.UID);

        registry.addRecipes(ITcRecipes.lithography_unit.getRecipes(), LithographyUnitCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.lithography_unit, LithographyUnitCategory.UID);
        registry.addRecipes(ITcRecipes.improved_lithography_unit.getRecipes(), ImprovedLithographyUnitCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.improved_lithography_unit, ImprovedLithographyUnitCategory.UID);

        registry.addRecipes(ITcRecipes.industrial_soldering_station.getRecipes(), IndustrialSolderingStationCategory.UID);
        addRecipeCatalyst(registry, ITcMachine.industrial_soldering_station, IndustrialSolderingStationCategory.UID);

        addRecipeCatalyst(registry, ITcMachine.industrial_alloy_furnace, "blast_furnace");
    }

    private void addRecipeCatalyst(IModRegistry registry, ITeBlock block, String uid) {
        registry.addRecipeCatalyst(block.getDummyTe().getBlockType().getItemStack(block), uid);
    }
}
