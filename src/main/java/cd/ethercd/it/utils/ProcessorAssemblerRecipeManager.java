package cd.ethercd.it.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import ic2.api.recipe.*;
import net.minecraft.item.ItemStack;
import java.util.Map;

public class ProcessorAssemblerRecipeManager {
    private final Table<ItemStack, ItemStack, ItemStack> recipesList = HashBasedTable.create();

    /**
     * Adds recipe to Mutagenesis Processor.
     * @param input1 Input first seeds
     * @param input2 Input second seeds
     * @param output Output seeds
     */
    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
        if (getResult(input1, input2) != ItemStack.EMPTY) return;
        recipesList.put(input1, input2, output);
    }

    /**
     * Adds recipe to Mutagenesis Processor.
     * @param input1 Input first seeds
     * @param input2 Input second seeds
     * @param output Output seeds
     * @param chance Chance of successful mutation
     */
    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack output, int chance) {
        if (getResult(input1, input2) != ItemStack.EMPTY) return;
        recipesList.put(input1, input2, output);
    }

    /**
     * Returns result from recipe
     * @param input1 Recipe input first slot
     * @param input2 Recipe inputs second slot
     * @return Result or ItemStack.EMPTY
     */
    public ItemStack getResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            if (input1.isItemEqual(entry.getKey()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input2.isItemEqual(ent.getKey()))
                        return ent.getValue();
            if (input2.isItemEqual(entry.getKey()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input1.isItemEqual(ent.getKey()))
                        return ent.getValue();
        }

        return ItemStack.EMPTY;
    }

    /**
     * Getting ItemStack without meta
     * @param stack Stack
     * @return Result
     */
    public ItemStack getStackWithoutMeta(ItemStack stack) {
        return new ItemStack(stack.getItem(), stack.getCount(), 0);
    }

    public Table<ItemStack, ItemStack, ItemStack> getRecipesList() {
        return recipesList;
    }
}
