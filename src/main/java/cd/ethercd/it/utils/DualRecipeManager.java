package cd.ethercd.it.utils;

import cd.ethercd.it.jei.machines.ProcessorAssemblerWrapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Map;

public class DualRecipeManager {
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
     * Returns result from recipe
     * @param input1 Recipe input first slot
     * @param input2 Recipe inputs second slot
     * @return Result or ItemStack.EMPTY
     */
    public ItemStack getResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            ItemStack key = entry.getKey();
            if (input1.isItemEqual(key) || (input1.isEmpty() && key.isEmpty()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input2.isItemEqual(ent.getKey()) && input2.getCount() >= ent.getKey().getCount())
                        return ent.getValue();
            if (input2.isItemEqual(key) || (input2.isEmpty() && key.isEmpty()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input1.isItemEqual(ent.getKey()) && input1.getCount() >= ent.getKey().getCount())
                        return ent.getValue();
        }
        return ItemStack.EMPTY;
    }

    public int[] getIngirientsConsume(ItemStack input1, ItemStack input2) {
        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            ItemStack key = entry.getKey();
            if (input1.isItemEqual(key) || (input1.isEmpty() && key.isEmpty()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input2.isItemEqual(ent.getKey()) && input2.getCount() >= key.getCount())
                        return new int[] {key.getCount(), ent.getKey().getCount()};
            if (input2.isItemEqual(key) || (input2.isEmpty() && key.isEmpty()))
                for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet())
                    if (input1.isItemEqual(ent.getKey()) && input1.getCount() >= key.getCount())
                        return new int[] {ent.getKey().getCount(), key.getCount()};
        }
        return new int[] {0, 0};
    }

    public List<ProcessorAssemblerWrapper> getRecipes() {
        List<ProcessorAssemblerWrapper> jeiRecipes = Lists.newArrayList();

        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                ItemStack input1 = entry.getKey();
                ItemStack input2 = ent.getKey();
                ItemStack output = ent.getValue();
                ProcessorAssemblerWrapper recipe = new ProcessorAssemblerWrapper(input1, input2, output);
                jeiRecipes.add(recipe);
            }
        }

        return jeiRecipes;
    }
}
