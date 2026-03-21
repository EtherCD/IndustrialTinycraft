package cd.ethercd.it.utils;

import cd.ethercd.it.jei.machines.ProcessOptimizerWrapper;
import cd.ethercd.it.jei.machines.ProcessorAssemblerWrapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ProcessOptimizerRecipeManager {
    private final List<ItemStack> recipesList = new ArrayList<>();

    /**
     * Adds recipe to Mutagenesis Processor.
     *
     * @param input1 Input first seeds
     * @param input2 Input second seeds
     * @param output Output seeds
     */
    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack input3, ItemStack output) {
        if (getResult(input1, input2, input3) != ItemStack.EMPTY) return;
        recipesList.add(input1);
        recipesList.add(input2);
        recipesList.add(input3);
        recipesList.add(output);
    }

    /**
     * Returns result from recipe
     *
     * @param input1 Recipe input first slot
     * @param input2 Recipe inputs second slot
     * @return Result or ItemStack.EMPTY
     */
    public ItemStack getResult(ItemStack input1, ItemStack input2, ItemStack input3) {
        for (int index = 0; index < recipesList.size(); index += 4) {
            ItemStack r1 = recipesList.get(index);
            ItemStack r2 = recipesList.get(index + 1);
            ItemStack r3 = recipesList.get(index + 2);
            ItemStack out = recipesList.get(index + 3);

            List<ItemStack> inputs = new ArrayList<>(Arrays.asList(input1, input2, input3));
            List<ItemStack> recipe = new ArrayList<>(Arrays.asList(r1, r2, r3));

            if (matches(inputs, recipe) && !out.isEmpty()) {
                return out.copy();
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean matches(List<ItemStack> inputs, List<ItemStack> recipe) {
        List<ItemStack> remaining = new ArrayList<>(inputs);

        for (ItemStack r : recipe) {
            boolean found = false;

            for (int i = 0; i < remaining.size(); i++) {
                if (ItemStack.areItemsEqual(remaining.get(i), r)) {
                    remaining.remove(i);
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }

        return remaining.isEmpty();
    }

    public int[] getIngirientsConsume(ItemStack input1, ItemStack input2, ItemStack input3) {
        for (int index = 0; index < recipesList.size(); index += 4) {
            ItemStack r1 = recipesList.get(index);
            ItemStack r2 = recipesList.get(index + 1);
            ItemStack r3 = recipesList.get(index + 2);
            ItemStack out = recipesList.get(index + 3);

            List<ItemStack> inputs = new ArrayList<>(Arrays.asList(input1, input2, input3));
            List<ItemStack> recipe = new ArrayList<>(Arrays.asList(r1, r2, r3));

            if (matches(inputs, recipe) && !out.isEmpty()) {
                return new int[] {r1.getCount(), r2.getCount(), r3.getCount()};
            }
        }
        return new int[] {0, 0, 0};
    }

    public List<ProcessOptimizerWrapper> getRecipes() {
        List<ProcessOptimizerWrapper> jeiRecipes = Lists.newArrayList();

        for (int index = 0; index < recipesList.size(); index += 4) {
            ItemStack r1 = recipesList.get(index);
            ItemStack r2 = recipesList.get(index + 1);
            ItemStack r3 = recipesList.get(index + 2);
            ItemStack out = recipesList.get(index + 3);
            ProcessOptimizerWrapper recipe = new ProcessOptimizerWrapper(r1, r2, r3, out);
            jeiRecipes.add(recipe);
        }

        return jeiRecipes;
    }
}
