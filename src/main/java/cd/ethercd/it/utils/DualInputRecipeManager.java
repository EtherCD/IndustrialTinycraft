package cd.ethercd.it.utils;

import cd.ethercd.it.jei.machines.DualInputRecipeWrapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.Map;

public class DualInputRecipeManager {
    protected final Table<ItemStack, ItemStack, ItemStack> recipesList = HashBasedTable.create();

    public void addRecipe(ItemStack input1, ItemStack input2, ItemStack output) {
        if (getResult(input1, input2) != ItemStack.EMPTY) return;
        recipesList.put(input1, input2, output);
    }

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

    public List<DualInputRecipeWrapper> getRecipes() {
        List<DualInputRecipeWrapper> jeiRecipes = Lists.newArrayList();

        for (Map.Entry<ItemStack, Map<ItemStack, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            for (Map.Entry<ItemStack, ItemStack> ent : entry.getValue().entrySet()) {
                ItemStack input1 = entry.getKey();
                ItemStack input2 = ent.getKey();
                ItemStack output = ent.getValue();
                DualInputRecipeWrapper recipe = new DualInputRecipeWrapper(input1, input2, output);
                jeiRecipes.add(recipe);
            }
        }

        return jeiRecipes;
    }
}
