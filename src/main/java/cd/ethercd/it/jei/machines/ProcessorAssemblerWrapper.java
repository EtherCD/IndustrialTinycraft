package cd.ethercd.it.jei.machines;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

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
        List<ItemStack> items = new ArrayList();
        items.add(firstInput);
        items.add(secondInput);
        ingredients.setInputs(ItemStack.class, items);
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