package cd.ethercd.it.jei.machines;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

public class ProcessorAssemblerWrapper implements IRecipeWrapper {
    private ItemStack firstInput;
    private ItemStack secondInput;
    private ItemStack output;

    public ProcessorAssemblerWrapper(ItemStack firstInput, ItemStack secondInput, ItemStack output) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInput(ItemStack.class, firstInput);
        ingredients.setInput(ItemStack.class, secondInput);
        ingredients.setOutput(ItemStack.class, output);
    }

    public ItemStack getFirstInput() {
        return firstInput;
    }

    public ItemStack getSecondInput() {
        return secondInput;
    }

    public ItemStack getOutput() {
        return output;
    }
}