package cd.ethercd.it.jei;

import cd.ethercd.it.ITcMachines;
import cd.ethercd.it.ITcRecipes;
import cd.ethercd.it.jei.machines.CrystalGrowerCategory;
import cd.ethercd.it.jei.machines.CrystalGrowerWrapper;
import cd.ethercd.it.jei.machines.ProcessorAssemblerCategory;
import com.google.common.collect.Lists;
import ic2.api.recipe.MachineRecipe;
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
        registry.addRecipeCategories(new ProcessorAssemblerCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        registry.addRecipes(Lists.newArrayList(ITcRecipes.crystal_grower.getRecipes()), CrystalGrowerCategory.UID);
        registry.handleRecipes(MachineRecipe.class, CrystalGrowerWrapper::new, CrystalGrowerCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ITcMachines.crystal_grower.getDummyTe().getBlockType().getItem()), CrystalGrowerCategory.UID);
        registry.addRecipes(ITcRecipes.processor_assembler.getRecipes(), ProcessorAssemblerCategory.UID);
        registry.addRecipeCatalyst(new ItemStack(ITcMachines.processor_assembler.getDummyTe().getBlockType().getItem()), ProcessorAssemblerCategory.UID);
    }
}
