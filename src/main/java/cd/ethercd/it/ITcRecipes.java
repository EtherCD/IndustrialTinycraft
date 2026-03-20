package cd.ethercd.it;

import cd.ethercd.it.block.BasicCraftBlock;
import cd.ethercd.it.items.BasicCraftItem;
import cd.ethercd.it.utils.ProcessOptimizerRecipeManager;
import cd.ethercd.it.utils.ProcessorAssemblerRecipeManager;
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
    public static ProcessorAssemblerRecipeManager processor_assembler = new ProcessorAssemblerRecipeManager();
    public static IBasicMachineRecipeManager processor_assembler_ic2_plug = new BasicMachineRecipeManager();
    public static ProcessOptimizerRecipeManager processs_optimizer = new ProcessOptimizerRecipeManager();
    public static IBasicMachineRecipeManager processs_optimizer_ic2_plug = new BasicMachineRecipeManager();
    public static IBasicMachineRecipeManager crystal_grower = new BasicMachineRecipeManager();

    public static void addBasicRecipes() {
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
        addBasicRecipe(BasicCraftItem.LITHOGRAPHY_LASER.getStack(),
                "GCG",
                "AMA",
                "GEG",
                'E', IC2Items.energy_crystal,
                'M', IC2Items.resource_advanced_machine,
                'A', IC2Items.crafting_alloy,
                'G', IC2Items.reinforced_stone,
                'C', IC2Items.advanced_circuit);
        // Upgrades
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_overclocker),
                "   ",
                "CCC",
                "PUP",
                'C', IC2Items.heat_storage,
                'P', BasicCraftItem.PROCESSOR_90NM.getStack(),
                'U', IC2Items.upgrade_overclocker);
        // Machines
        addBasicRecipe(ITcMachines.crystal_grower.getStack(),
                "WSW",
                "BCB",
                "WSW",
                'W', IC2Items.cable_copper,
                'B', IC2Items.resource_machine,
                'S', IC2Items.advanced_circuit,
                'C', IC2Items.heat_storage);
        addBasicRecipe(ITcMachines.processor_assembler.getStack(),
                "BCB",
                "PGL",
                "BEB",
                'B', IC2Items.resource_advanced_machine,
                'C', IC2Items.advanced_circuit,
                'L', BasicCraftItem.SILICON_PLATE.getStack(),
                'G', IC2Items.reinfored_glass,
                'P', BasicCraftItem.LITHOGRAPHY_LASER.getStack(),
                'E', IC2Items.lapotron_crystal);
        // Substrates
        addBasicRecipe(BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack(),
                " S ",
                "BCB",
                " S ",
                'B', BasicCraftItem.PURIFIED_GOLD_DUST.getStack(),
                'S', IC2Items.circuit,
                'C', BasicCraftItem.PROCESSOR_SUBSTRATE.getStack());
        addBasicRecipe(BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(),
                " S ",
                "BCB",
                " S ",
                'B', BasicCraftItem.PURIFIED_DIAMOND_DUST.getStack(),
                'S', IC2Items.advanced_circuit,
                'C', BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack());
        // Heat Storages
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_heat_storage),
                " S ",
                "BCB",
                " S ",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_90NM.getStack(),
                'C', IC2Items.heat_storage);
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                "BBB",
                "SSB",
                "BCB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.PROCESSOR_90NM.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_hex_heat_storage),
                "BSB",
                "BCB",
                "BIB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                'I', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.PROCESSOR_90NM.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'C', IC2Items.tri_heat_storage);
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_hex_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'C', IC2Items.hex_heat_storage);
        // Advanced heat storage
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_heat_storage),
                " S ",
                "BCB",
                " S ",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_45NM.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_heat_storage));
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                "BBB",
                "SSB",
                "BCB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.advanced_heat_storage),
                'C', BasicCraftItem.PROCESSOR_45NM.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_hex_heat_storage),
                "BSB",
                "BCB",
                "BIB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                'I', new ItemStack(ITcItemLoader.advanced_heat_storage),
                'C', BasicCraftItem.PROCESSOR_45NM.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_tri_heat_storage));
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_hex_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_hex_heat_storage));
        // Unstable/Stabilized Energy Core
        addBasicRecipe(BasicCraftItem.UNSTABLE_ENERGY_CORE.getStack(),
                "URU",
                "RCR",
                "URU",
                'R', BasicCraftItem.PURIFIED_REDSTONE.getStack(),
                'C', BasicCraftItem.PROCESSOR_45NM.getStack(),
                'U', IC2Items.nuclear_small_uranium_235);
        addBasicRecipe(BasicCraftItem.STABILIZED_ENERGY_CORE.getStack(),
                "CHC",
                "HUH",
                "CHC",
                'H', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'U', BasicCraftItem.UNSTABLE_ENERGY_CORE.getStack());
        // Parallel
    }

    public static void addMachineRecipes() {
        IRecipeInputFactory factory = Recipes.inputFactory;

        addCompressorRecipe(factory.forStack(BasicCraftItem.GLASS_DUST.getStack(), 4), BasicCraftItem.FIBERGLASS.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack()), BasicCraftItem.PROCESSOR_SUBSTRATE.getStack());

        addMaceratorRecipe(factory.forStack(new ItemStack(Blocks.GLASS)), BasicCraftItem.GLASS_DUST.getStack());
        addMaceratorRecipe(factory.forStack(BasicCraftItem.FIBERGLASS.getStack()), BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack());
        addMaceratorRecipe(factory.forStack(BasicCraftBlock.CYRTOLITE_ORE.getStack()), BasicCraftItem.CRUSHED_CYRTOLITE_ORE.getStack());

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("amount", 250);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_copper), BasicCraftItem.PURIFIED_COPPER_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_diamond), BasicCraftItem.PURIFIED_DIAMOND_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_gold), BasicCraftItem.PURIFIED_GOLD_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_silicon_dioxide), BasicCraftItem.PURIFIED_SILICON.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(new ItemStack(Items.REDSTONE)), BasicCraftItem.PURIFIED_REDSTONE.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(BasicCraftItem.HAFNIUM_DUST.getStack()), BasicCraftItem.PURIFIED_HAFNIUM_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(BasicCraftItem.CRUSHED_CYRTOLITE_ORE.getStack()), BasicCraftItem.PURIFIED_CYRTOLITE_ORE.getStack(), nbt);

        addExtrudingRecipe(factory.forStack(BasicCraftItem.SILICON_INGOT.getStack()), BasicCraftItem.SILICON_PLATE.getStack());

        addCentrifugeRecipe(factory.forStack(BasicCraftItem.PURIFIED_CYRTOLITE_ORE.getStack()), BasicCraftItem.HAFNIUM_DUST.getStack(2));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.CRUSHED_CYRTOLITE_ORE.getStack()), BasicCraftItem.HAFNIUM_DUST.getStack(), IC2Items.dust_stone);

        addCrystalGrowerRecipe(factory.forStack(BasicCraftItem.PURIFIED_SILICON.getStack()), BasicCraftItem.SILICON_INGOT.getStack());

        addProcessorAssemblerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_90NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(2), ItemStack.EMPTY, BasicCraftItem.PROCESSOR_90NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_90NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_90NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_45NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_45NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_45NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_22NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_22NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_22NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack(), BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_7NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.PHOTONIC_COMPUTING_ACCELERATOR.getStack(), BasicCraftItem.PROCESSOR_7NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_7NM.getStack());

        addProcessOptimizerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.PURIFIED_REDSTONE.getStack(), ItemStack.EMPTY, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack());
        addProcessOptimizerRecipe(factory, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.PURIFIED_COPPER_DUST.getStack(), BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack(), BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack());
        addProcessOptimizerRecipe(factory, BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.PURIFIED_HAFNIUM_DUST.getStack(), ItemStack.EMPTY, BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack());
    }

    private static void addBasicRecipe(ItemStack output, Object... input) {
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

    private static void addExtrudingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerExtruding.addRecipe(input, null, false, output);
    }

    private static void addCentrifugeRecipe(IRecipeInput input, ItemStack... output) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("minHeat", 600);
        Recipes.centrifuge.addRecipe(input, nbt, false, output);
    }

    private static void addProcessorAssemblerRecipe(IRecipeInputFactory factory, ItemStack firstInput, ItemStack secondInput, ItemStack output) {
        ITcRecipes.processor_assembler.addRecipe(firstInput, secondInput, output);
        if (!firstInput.isEmpty())
            ITcRecipes.processor_assembler_ic2_plug.addRecipe(factory.forStack(firstInput, firstInput.getCount()), null, false, output);
        if (!secondInput.isEmpty())
            ITcRecipes.processor_assembler_ic2_plug.addRecipe(factory.forStack(secondInput), null, false, output);
    }

    private static void addProcessOptimizerRecipe(IRecipeInputFactory factory, ItemStack firstInput, ItemStack secondInput, ItemStack tridInput, ItemStack output) {
        ITcRecipes.processs_optimizer.addRecipe(firstInput, secondInput, tridInput, output);
        if (!firstInput.isEmpty())
            ITcRecipes.processs_optimizer_ic2_plug.addRecipe(factory.forStack(firstInput), null, false, output);
        if (!secondInput.isEmpty())
            ITcRecipes.processs_optimizer_ic2_plug.addRecipe(factory.forStack(secondInput), null, false, output);
        if (!tridInput.isEmpty())
            ITcRecipes.processs_optimizer_ic2_plug.addRecipe(factory.forStack(tridInput), null, false, output);
    }

    private static void addCrystalGrowerRecipe(IRecipeInput input, ItemStack output) {
        ITcRecipes.crystal_grower.addRecipe(input, null, false, output);
    }
}
