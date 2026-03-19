package cd.ethercd.it;

import cd.ethercd.it.items.BasicCraftItem;
import ic2.api.recipe.IBasicMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.IRecipeInputFactory;
import ic2.api.recipe.Recipes;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ITcRecipes {
    public static IBasicMachineRecipeManager processor_assembler = new BasicMachineRecipeManager();

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
        addBasicRecipe(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack(),
                " G ",
                " R ",
                " F ",
                'G', IC2Items.plate_gold,
                'R', IC2Items.rubber,
                'F', BasicCraftItem.FIBERGLASS.getStack());
        addBasicRecipe(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack(),
                "G  ",
                "R  ",
                "F  ",
                'G', IC2Items.plate_gold,
                'R', IC2Items.rubber,
                'F', BasicCraftItem.FIBERGLASS.getStack());
        addBasicRecipe(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack(),
                "  G",
                "  R",
                "  F",
                'G', IC2Items.plate_gold,
                'R', IC2Items.rubber,
                'F', BasicCraftItem.FIBERGLASS.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_overclocker),
                "   ",
                "CCC",
                "PUP",
                'C', IC2Items.heat_storage,
                'P', BasicCraftItem.PROCESSOR_90NM.getStack(),
                'U', IC2Items.upgrade_overclocker);
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

        addProcessorAssemblerRecipe(factory.forStack(BasicCraftItem.BASIC_SEMICONDUCTOR_WAFFLE.getStack()), BasicCraftItem.BASIC_PROCESSOR_CHIP.getStack());
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

    private static void addProcessorAssemblerRecipe(IRecipeInput input, ItemStack output) {
        ITcRecipes.processor_assembler.addRecipe(input, null, false, output);
    }
}
