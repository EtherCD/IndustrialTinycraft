package com.ethercd.it;

import com.ethercd.it.items.BasicCraftItem;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Crafting {
    public static void addBasicRecipes() {
        addBasicRecipe(IC2Items.reinfored_glass,
                "BCB",
                "LGP",
                "BCB",
                'B', IC2Items.reinforced_stone,
                'C', IC2Items.advanced_circuit,
                'L', IC2Items.mining_laser,
                'G', IC2Items.reinfored_glass,
                'P', IC2Items.mining_laser);
        addBasicRecipe(IC2Items.reinfored_glass,
                " G ",
                " R ",
                " F ",
                'G', IC2Items.plate_gold,
                'R', IC2Items.rubber,
                'F', BasicCraftItem.FIBERGLASS.getStack());
    }

    public static void addMachineRecipes() {
        IRecipeInputFactory factory = Recipes.inputFactory;

        addCompressorRecipe(factory.forStack(BasicCraftItem.PURIFIED_SILICON.getStack(), 4), BasicCraftItem.BASIC_SEMICONDUCTOR_WAFFLE.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.GLASS_DUST.getStack(), 4), BasicCraftItem.FIBERGLASS.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack()), BasicCraftItem.PROCESSOR_SUBSTRATE.getStack());

        addMaceratorRecipe(factory.forStack(new ItemStack(Items.FLINT)), BasicCraftItem.CRUSHED_SILICON.getStack());
        addMaceratorRecipe(factory.forStack(new ItemStack(Blocks.GLASS)), BasicCraftItem.GLASS_DUST.getStack());

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("amount", 250);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_copper), BasicCraftItem.PURIFIED_COPPER_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_diamond), BasicCraftItem.PURIFIED_DIAMOND_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_gold), BasicCraftItem.PURIFIED_GOLD_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_silicon_dioxide), BasicCraftItem.PURIFIED_SILICON.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(BasicCraftItem.CRUSHED_SILICON.getStack()), BasicCraftItem.PURIFIED_SILICON.getStack(), nbt);
    }

    private static void addBasicRecipe(ItemStack output, Object... input) {
        System.out.println(input);
        Recipes.advRecipes.addRecipe(output, input);
    }

    private static void addCompressorRecipe(IRecipeInput input, ItemStack output)  {
        Recipes.compressor.addRecipe(input, null, false, output);
    }

    private static void addMaceratorRecipe(IRecipeInput input, ItemStack output) {
        Recipes.macerator.addRecipe(input, null, false, output);
    }

    private static void addOreWashingRecipe(IRecipeInput input, ItemStack output, NBTTagCompound nbt) {
        Recipes.oreWashing.addRecipe(input, nbt, false, output);
    }
}
