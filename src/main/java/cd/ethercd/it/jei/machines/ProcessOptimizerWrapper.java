package cd.ethercd.it.jei.machines;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ProcessOptimizerWrapper implements IRecipeWrapper {
    private final ItemStack firstInput;
    private final ItemStack secondInput;
    private final ItemStack tridInput;
    private final ItemStack output;

    public ProcessOptimizerWrapper(ItemStack firstInput, ItemStack secondInput, ItemStack tridInput, ItemStack output) {
        this.firstInput = firstInput;
        this.secondInput = secondInput;
        this.tridInput = tridInput;
        this.output = output;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> items = new ArrayList<>();
        items.add(firstInput);
        items.add(secondInput);
        items.add(tridInput);
        ingredients.setInputs(ItemStack.class, items);
        ingredients.setOutput(ItemStack.class, output);
    }

    public ItemStack getOutput() {
        return output;
    }
}