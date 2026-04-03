package cd.ethercd.it.utils;

import cd.ethercd.it.jei.machines.DualInputRecipeWrapper;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Lists;
import com.google.common.collect.Table;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.crafting.IRecipeFactory;

import java.util.List;
import java.util.Map;

public class LithographyRecipeManager {
    private final Table<LithographyRecipeInput, LithographyRecipeInput, ItemStack> recipesList = HashBasedTable.create();
    public final IBasicMachineRecipeManager ic2_input_plug = new BasicMachineRecipeManager();
    public final IBasicMachineRecipeManager ic2_mask_input_plug = new BasicMachineRecipeManager();

    public void addRecipe(IRecipeInputFactory factory, LithographyRecipeInput mask, LithographyRecipeInput plate, ItemStack output) {
        if (getResult(mask.input, plate.input) != ItemStack.EMPTY) return;
        recipesList.put(mask, plate, output);
        ic2_mask_input_plug.addRecipe(factory.forStack(mask.input), null, false, output);
        ic2_input_plug.addRecipe(factory.forStack(plate.input), null, false, output);
    }

    public ItemStack getResult(ItemStack input1, ItemStack input2) {
        for (Map.Entry<LithographyRecipeInput, Map<LithographyRecipeInput, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            ItemStack key = entry.getKey().input;
            if (input1.isItemEqual(key) || (input1.isEmpty() && key.isEmpty()))
                for (Map.Entry<LithographyRecipeInput, ItemStack> ent : entry.getValue().entrySet())
                    if (input2.isItemEqual(ent.getKey().input) && input2.getCount() >= ent.getKey().input.getCount())
                        return ent.getValue();
            if (input2.isItemEqual(key) || (input2.isEmpty() && key.isEmpty()))
                for (Map.Entry<LithographyRecipeInput, ItemStack> ent : entry.getValue().entrySet())
                    if (input1.isItemEqual(ent.getKey().input) && input1.getCount() >= ent.getKey().input.getCount())
                        return ent.getValue();
        }
        return ItemStack.EMPTY;
    }

    public int getPlateConsume(ItemStack plate) {
        for (Map.Entry<LithographyRecipeInput, Map<LithographyRecipeInput, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            for (Map.Entry<LithographyRecipeInput, ItemStack> ent : entry.getValue().entrySet())
                if (plate.isItemEqual(ent.getKey().input))
                    return entry.getKey().input.getCount();
        }
        return 0;
    }

    public List<DualInputRecipeWrapper> getRecipes() {
        List<DualInputRecipeWrapper> jeiRecipes = Lists.newArrayList();

        for (Map.Entry<LithographyRecipeInput, Map<LithographyRecipeInput, ItemStack>> entry : recipesList.columnMap().entrySet()) {
            for (Map.Entry<LithographyRecipeInput, ItemStack> ent : entry.getValue().entrySet()) {
                ItemStack input1 = entry.getKey().input;
                ItemStack input2 = ent.getKey().input;
                ItemStack output = ent.getValue();
                DualInputRecipeWrapper recipe = new DualInputRecipeWrapper(input1, input2, output);
                jeiRecipes.add(recipe);
            }
        }

        return jeiRecipes;
    }

    public static class LithographyRecipeInput {
        boolean saveAfterProcess;
        ItemStack input;
        public LithographyRecipeInput(ItemStack input, boolean saveAfterProcess) {
            this.input = input;
            this.saveAfterProcess = saveAfterProcess;
        }
    }
}
