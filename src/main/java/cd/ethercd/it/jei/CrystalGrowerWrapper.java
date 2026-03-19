package cd.ethercd.it.jei;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipe;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.Collection;

public class CrystalGrowerWrapper implements IRecipeWrapper {
    private ItemStack input;
    private ItemStack output;

    public CrystalGrowerWrapper(MachineRecipe<IRecipeInput, ItemStack> recipe) {
        input = recipe.getInput().getInputs().get(0);
        output = recipe.getOutput();
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, input);
        ingredients.setOutput(ItemStack.class, output);
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }
}