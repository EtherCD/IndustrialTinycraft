package cd.ethercd.it;

import cd.ethercd.it.blocks.ITcResource;
import cd.ethercd.it.items.BasicCraftItem;
import cd.ethercd.it.items.BasicItem;
import cd.ethercd.it.utils.LithographyRecipeManager;
import cd.ethercd.it.utils.TriInputRecipeManager;
import cd.ethercd.it.utils.DualInputRecipeManager;
import ic2.api.recipe.*;
import ic2.core.recipe.BasicMachineRecipeManager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class ITcRecipes {
    public static LithographyRecipeManager lithography_unit = new LithographyRecipeManager();
    public static LithographyRecipeManager improved_lithography_unit = new LithographyRecipeManager();
    public static TriInputRecipeManager industrial_soldering_station = new TriInputRecipeManager();
    public static IBasicMachineRecipeManager crystal_grower = new BasicMachineRecipeManager();

    private static void addCycleRecipeForDust(BasicCraftItem dust, BasicCraftItem small) {
        addBasicRecipe(dust.getStack(),
                "SSS",
                "SSS",
                "SSS",
                'S', small.getStack());
        addBasicRecipe(small.getStack(9),
                "   ",
                " S ",
                "   ",
                'S', dust.getStack());
    }

    private static void addRecipeForRod(BasicItem rod, BasicItem dual_rod, BasicItem quad_rod) {
        addBasicRecipe(new ItemStack(dual_rod),
                "   ",
                "FPF",
                "   ",
                'F', new ItemStack(rod),
                'P', IC2Items.plate_iron);
        addBasicRecipe(new ItemStack(quad_rod),
                "FPF",
                "CPC",
                "FPF",
                'F', new ItemStack(rod),
                'P', IC2Items.plate_iron,
                'C', IC2Items.plate_copper);
        addBasicRecipe(new ItemStack(quad_rod),
                " F ",
                "CPC",
                " F ",
                'F', new ItemStack(dual_rod),
                'P', IC2Items.plate_iron,
                'C', IC2Items.plate_copper);
    }


    private static void addFullPlateCraft(ItemStack output, ItemStack plate1, ItemStack plate2, ItemStack plate3) {
        addBasicRecipe(output,
                "GGG",
                "RRR",
                "FFF",
                'G', plate1,
                'R', plate2,
                'F', plate3);
    }

    public static void addBasicRecipes() {
        // Radioactive
        addCycleRecipeForDust(BasicCraftItem.HAFNIUM_DUST, BasicCraftItem.HAFNIUM_SMALL_DUST);
        addCycleRecipeForDust(BasicCraftItem.ZIRCONIUM_DUST, BasicCraftItem.ZIRCONIUM_SMALL_DUST);
        addCycleRecipeForDust(BasicCraftItem.TECHNETIUM_DUST, BasicCraftItem.TECHNETIUM_SMALL_DUST);
        addCycleRecipeForDust(BasicCraftItem.MOLYBDENUM_DUST, BasicCraftItem.MOLYBDENUM_SMALL_DUST);
        addBasicRecipe(BasicCraftItem.TECHNETIUM_FUEL.getStack(),
                " M ",
                "MTM",
                " M ",
                'T', BasicCraftItem.TECHNETIUM_DUST.getStack(),
                'M', BasicCraftItem.MOLYBDENUM_SMALL_DUST.getStack());
        addRecipeForRod(ITcItemLoader.technetium_rod, ITcItemLoader.technetium_dual_rod, ITcItemLoader.technetium_quad_rod);
        addRecipeForRod(ITcItemLoader.molybdenum_rod, ITcItemLoader.molybdenum_dual_rod, ITcItemLoader.molybdenum_quad_rod);

        // Resource Crafting
        addBasicRecipe(ITcResource.PERFECT_MACHINE_CASING.getStack(),
                "CPI",
                "TAT",
                "IPC",
                'C', IC2Items.carbon_plate,
                'P', BasicCraftItem.BASIC_PROCESSOR.getStack(),
                'A', IC2Items.resource_advanced_machine,
                'T', BasicCraftItem.TECHNETIUM_DUST.getStack(),
                'I', IC2Items.iridium_plate);

        addBasicRecipe(BasicCraftItem.UNFILLED_LITHIUM_BATTERY.getStack(),
                "WIP",
                "CFG",
                "PRP",
                'G', BasicCraftItem.GRAPHITE_PLATE.getStack(),
                'F', BasicCraftItem.FIBERGLASS.getStack(),
                'W', IC2Items.cable_copper,
                'C', IC2Items.plate_copper,
                'P', IC2Items.plate_iron,
                'I', BasicCraftItem.BASIC_PROCESSOR.getStack(),
                'R', IC2Items.rubber);
        addFullPlateCraft(BasicCraftItem.MOLYBDENUM_ALLOY_DUST.getStack(2), BasicCraftItem.MOLYBDENUM_DUST.getStack(), IC2Items.dust_iron, IC2Items.dust_coal_fuel);

        addBasicRecipe(BasicCraftItem.LITHIUM_SULFURIC_MIXTURE.getStack(2),
                "SSS",
                "BBB",
                "SSS",
                'B', IC2Items.dust_lithium,
                'S', IC2Items.dust_sulfuric);

        addBasicRecipe(BasicCraftItem.FUSION_CORE.getStack(),
                "SEP",
                "CMC",
                "PES",
                'E', IC2Items.energy_crystal,
                'M', BasicCraftItem.UNSTABLE_ENERGY_CORE.getStack(),
                'P', IC2Items.carbon_plate,
                'C', IC2Items.advanced_circuit,
                'S', BasicCraftItem.MOLYBDENUM_ALLOY_PLATE.getStack());
        // Upgrades
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_overclocker),
                "   ",
                "CCC",
                "PUP",
                'C', new ItemStack(ITcItemLoader.improved_heat_storage),
                'P', BasicCraftItem.BASIC_PROCESSOR.getStack(),
                'U', IC2Items.upgrade_overclocker);
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_overclocker),
                "   ",
                "CCC",
                "PUP",
                'C', new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                'P', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'U', new ItemStack(ITcItemLoader.improved_overclocker));
//        addBasicRecipe(new ItemStack(ITcItemLoader.unrivaled_overclocker),
//                "MPH",
//                "TOT",
//                "HPM",
//                'M', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
//                'H', BasicCraftItem.DENSE_HAFNIUM_PLATE.getStack(),
//                'T', new ItemStack(ITcItemLoader.advanced_hex_heat_storage),
//                'P', BasicCraftItem.PROCESSOR_7NM.getStack(),
//                'O', new ItemStack(ITcItemLoader.advanced_overclocker));
        // Machines
        // Heat Storages
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_heat_storage),
                " S ",
                "BCB",
                " S ",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.BASIC_PROCESSOR.getStack(),
                'C', IC2Items.heat_storage);
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                "BBB",
                "SSB",
                "BCB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.BASIC_PROCESSOR.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_hex_heat_storage),
                "BSB",
                "BCB",
                "BIB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                'I', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.BASIC_PROCESSOR.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'C', IC2Items.tri_heat_storage);
        addBasicRecipe(new ItemStack(ITcItemLoader.improved_hex_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'C', IC2Items.hex_heat_storage);
        // Advanced heat storage
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_heat_storage),
                " S ",
                "BCB",
                " S ",
                'B', IC2Items.plate_tin,
                'S', BasicCraftItem.IMPROVED_PROCESSOR.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_heat_storage));
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                "BBB",
                "SSB",
                "BCB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.advanced_heat_storage),
                'C', BasicCraftItem.IMPROVED_PROCESSOR.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_hex_heat_storage),
                "BSB",
                "BCB",
                "BIB",
                'B', IC2Items.plate_tin,
                'S', new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                'I', new ItemStack(ITcItemLoader.advanced_heat_storage),
                'C', BasicCraftItem.IMPROVED_PROCESSOR.getStack());
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_tri_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.dense_plate_tin,
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_tri_heat_storage));
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_hex_heat_storage),
                "BSB",
                "BCB",
                "BSB",
                'B', IC2Items.dense_plate_tin,
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'C', new ItemStack(ITcItemLoader.improved_hex_heat_storage));
        // Unstable/Stabilized Energy Core
        addBasicRecipe(BasicCraftItem.UNSTABLE_ENERGY_CORE.getStack(),
                "URU",
                "RCR",
                "URU",
                'R', BasicCraftItem.PURIFIED_REDSTONE.getStack(),
                'C', BasicCraftItem.TECHNETIUM_DUST.getStack(),
                'U', IC2Items.nuclear_small_uranium_235);
        addBasicRecipe(BasicCraftItem.STABILIZED_ENERGY_CORE.getStack(),
                "CHC",
                "HUH",
                "CHC",
                'H', new ItemStack(ITcItemLoader.improved_heat_storage),
                'C', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'U', BasicCraftItem.UNSTABLE_ENERGY_CORE.getStack());
        // Parallel
        addBasicRecipe(new ItemStack(ITcItemLoader.parallel_processing_upgrade),
                "TCT",
                "PMP",
                "TCT",
                'T', IC2Items.plate_tin,
                'C', IC2Items.tri_heat_storage,
                'P', BasicCraftItem.IMPROVED_PROCESSOR.getItem(),
                'M', IC2Items.resource_machine);
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_parallel_processing_upgrade),
                "TCT",
                "PAP",
                "TCT",
                'T', IC2Items.plate_steel,
                'C', new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                'P', BasicCraftItem.ADVANCED_PROCESSOR.getItem(),
                'A', IC2Items.resource_advanced_machine);

        // Centrifuge Upgrade
        addBasicRecipe(new ItemStack(ITcItemLoader.always_on_centrifuge),
                "PCP",
                "MSM",
                "PCP",
                'P', IC2Items.plate_steel,
                'C', IC2Items.coil,
                'M', BasicCraftItem.BASIC_PROCESSOR.getStack(),
                'S', IC2Items.upgrade_energy_storage);

        // Nuclear

        // Neutron moderator
        addBasicRecipe(new ItemStack(ITcItemLoader.neutron_moderator),
                "CHC",
                "HUH",
                "CHC",
                'H', IC2Items.plate_copper,
                'C', BasicCraftItem.ZIRCONIUM_PLATE.getStack(),
                'U', IC2Items.dust_coal_fuel);


        // Lithium MFSU
        addBasicRecipe(ITcEnergyStorage.lithium_mfsu.getStack(),
                "PLP",
                "SMS",
                "PLP",
                'P', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'L', new ItemStack(ITcItemLoader.lithium_battery_assembly),
                'M', IC2Items.mfsu);
        addBasicRecipe(ITcEnergyStorage.lithium_mfsu.getStack(),
                "PLP",
                "SMS",
                "PLP",
                'P', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'L', new ItemStack(ITcItemLoader.lithium_battery_assembly),
                'M', IC2Items.chargepad_mfsu);
        // Industrial Alloy Furance
        addBasicRecipe(ITcMachine.industrial_alloy_furnace.getStack(),
                "PCQ",
                "SBS",
                "QEP",
                'P', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
                'B', IC2Items.blast_furnace,
                'E', IC2Items.electric_heat_generator,
                'S', BasicCraftItem.ADVANCED_PROCESSOR.getStack(),
                'C', new ItemStack(ITcItemLoader.lithium_battery),
                'Q', ITcResource.PERFECT_MACHINE_CASING.getStack());
    }

    public static void addMachineRecipes() {
        IRecipeInputFactory factory = Recipes.inputFactory;

        addCompressorRecipe(factory.forStack(BasicCraftItem.GLASS_DUST.getStack(), 4), BasicCraftItem.FIBERGLASS.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack(9)), BasicCraftItem.FIBER_OPTIC_PLATE.getStack());
        addCompressorRecipe(factory.forStack(IC2Items.dust_coal_fuel, 4), BasicCraftItem.GRAPHITE_PLATE.getStack());
        addCompressorRecipe(factory.forStack(new ItemStack(ITcItemLoader.lithium_battery), 9), new ItemStack(ITcItemLoader.lithium_battery_assembly));
        addCompressorRecipe(factory.forStack(BasicCraftItem.MOLYBDENUM_ALLOY_PLATE.getStack(), 9), BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.HAFNIUM_PLATE.getStack(), 9), BasicCraftItem.DENSE_HAFNIUM_PLATE.getStack());

        addMaceratorRecipe(factory.forStack(new ItemStack(Blocks.GLASS)), BasicCraftItem.GLASS_DUST.getStack());
        addMaceratorRecipe(factory.forStack(BasicCraftItem.FIBERGLASS.getStack()), BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack());
        addMaceratorRecipe(factory.forStack(ITcResource.CYRTOLITE_ORE.getStack()), BasicCraftItem.CRUSHED_CYRTOLITE_ORE.getStack());
        addMaceratorRecipe(factory.forStack(ITcResource.WULFENITE_ORE.getStack()), BasicCraftItem.CRUSHED_WULFENITE_ORE.getStack());
        addMaceratorRecipe(factory.forStack(BasicCraftItem.HAFNIUM_INGOT.getStack()), BasicCraftItem.HAFNIUM_DUST.getStack());
        addMaceratorRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_INGOT.getStack()), BasicCraftItem.ZIRCONIUM_DUST.getStack());

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("amount", 250);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_copper), BasicCraftItem.PURIFIED_COPPER_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_diamond), BasicCraftItem.PURIFIED_DIAMOND_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_gold), BasicCraftItem.PURIFIED_GOLD_DUST.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(IC2Items.dust_silicon_dioxide), BasicCraftItem.PURIFIED_SILICON.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(new ItemStack(Items.REDSTONE)), BasicCraftItem.PURIFIED_REDSTONE.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(BasicCraftItem.CRUSHED_CYRTOLITE_ORE.getStack()), BasicCraftItem.PURIFIED_CYRTOLITE_ORE.getStack(), nbt);
        addOreWashingRecipe(factory.forStack(BasicCraftItem.CRUSHED_WULFENITE_ORE.getStack()), BasicCraftItem.PURIFIED_WULFENITE_ORE.getStack(), nbt);

        addRollingRecipe(factory.forStack(BasicCraftItem.SILICON_INGOT.getStack()), BasicCraftItem.SILICON_PLATE.getStack());
        addRollingRecipe(factory.forStack(BasicCraftItem.HAFNIUM_INGOT.getStack()), BasicCraftItem.HAFNIUM_PLATE.getStack());
        addRollingRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_INGOT.getStack()), BasicCraftItem.ZIRCONIUM_PLATE.getStack());
        addRollingRecipe(factory.forStack(BasicCraftItem.MOLYBDENUM_ALLOY_INGOT.getStack()), BasicCraftItem.MOLYBDENUM_ALLOY_PLATE.getStack());

        addExtrudingRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_PLATE.getStack()), BasicCraftItem.ZIRCONIUM_ROD.getStack());

        addCentrifugeRecipe(factory.forStack(BasicCraftItem.PURIFIED_CYRTOLITE_ORE.getStack()), BasicCraftItem.ZIRCONIUM_SMALL_DUST.getStack(2), BasicCraftItem.HAFNIUM_SMALL_DUST.getStack(), IC2Items.dust_stone);
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.PURIFIED_WULFENITE_ORE.getStack()), BasicCraftItem.MOLYBDENUM_DUST.getStack(1), BasicCraftItem.MOLYBDENUM_SMALL_DUST.getStack(8), IC2Items.dust_stone);

        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_MOLYBDENUM.getStack()), BasicCraftItem.MOLYBDENUM_SMALL_DUST.getStack(7), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(3));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_DUAL_MOLYBDENUM.getStack()), BasicCraftItem.MOLYBDENUM_DUST.getStack(1), BasicCraftItem.MOLYBDENUM_SMALL_DUST.getStack(1), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(8));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_QUAD_MOLYBDENUM.getStack()), BasicCraftItem.MOLYBDENUM_DUST.getStack(2), BasicCraftItem.TECHNETIUM_DUST.getStack(1), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(6));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_ZIRCONIUM_TECHNETIUM.getStack()), BasicCraftItem.ZIRCONIUM_SMALL_DUST.getStack(9), BasicCraftItem.HAFNIUM_SMALL_DUST.getStack(4), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(6));

        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_TECHNETIUM.getStack()), BasicCraftItem.TECHNETIUM_DUST.getStack(1), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(4));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_DUAL_TECHNETIUM.getStack()), BasicCraftItem.TECHNETIUM_DUST.getStack(2), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(8));
        addCentrifugeRecipe(factory.forStack(BasicCraftItem.DEPLETED_QUAD_TECHNETIUM.getStack()), BasicCraftItem.TECHNETIUM_DUST.getStack(5), BasicCraftItem.TECHNETIUM_SMALL_DUST.getStack(3));

        addBlastRecipe(factory.forStack(BasicCraftItem.HAFNIUM_DUST.getStack()), BasicCraftItem.HAFNIUM_INGOT.getStack());
        addBlastRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_DUST.getStack()), BasicCraftItem.ZIRCONIUM_INGOT.getStack());
        addBlastRecipe(factory.forStack(BasicCraftItem.MOLYBDENUM_ALLOY_DUST.getStack()), BasicCraftItem.MOLYBDENUM_ALLOY_INGOT.getStack());

        addCannerEnrichRecipe(new FluidStack(FluidRegistry.WATER, 1000), factory.forStack(BasicCraftItem.LITHIUM_SULFURIC_MIXTURE.getStack()), new FluidStack(ITcFluid.LITHIUM_ELECTROLYTE.getFluid(), 1000));

        addCannerBottleRecipe(factory.forFluidContainer(ITcFluid.LITHIUM_ELECTROLYTE.getFluid()), factory.forStack(BasicCraftItem.UNFILLED_LITHIUM_BATTERY.getStack()), new ItemStack(ITcItemLoader.lithium_battery));
        addCannerBottleRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_ROD.getStack()), factory.forStack(BasicCraftItem.MOLYBDENUM_DUST.getStack()), new ItemStack(ITcItemLoader.molybdenum_rod));
        addCannerBottleRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_ROD.getStack()), factory.forStack(BasicCraftItem.TECHNETIUM_FUEL.getStack()), new ItemStack(ITcItemLoader.technetium_rod));
        addCannerBottleRecipe(factory.forStack(BasicCraftItem.ZIRCONIUM_ROD.getStack()), factory.forStack(BasicCraftItem.ZIRCONIUM_TECHNETIUM_MIXTURE.getStack()), new ItemStack(ITcItemLoader.zirconium_technetium_rod));
        addCannerBottleRecipe(factory.forStack(IC2Items.dust_iron, 2), factory.forStack(BasicCraftItem.FIBERGLASS.getStack()), BasicCraftItem.TEXTOLITE.getStack());

        addCrystalGrowerRecipe(factory.forStack(BasicCraftItem.PURIFIED_SILICON.getStack()), BasicCraftItem.SILICON_INGOT.getStack());

        addLithographyUnitRecipe(factory, BasicCraftItem.MICROCONTROLLER_LITHOGRAPHY_MASK.getStack(), BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.MICROCONTROLLER_CHIP.getStack());
        addLithographyUnitRecipe(factory, BasicCraftItem.BASIC_PROCESSOR_LITHOGRAPHIC_MASK.getStack(), BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.BASIC_PROCESSOR_CHIP.getStack());
        addLithographyUnitRecipe(factory, BasicCraftItem.IMPROVED_PROCESSOR_LITHOGRAPHIC_MASK.getStack(), BasicCraftItem.IMPROVED_SILICON_PLATE.getStack(), BasicCraftItem.IMPROVED_PROCESSOR_CHIP.getStack());
        addImprovedLithographyUnitRecipe(factory, BasicCraftItem.ADVANCED_PROCESSOR_LITHOGRAPHIC_MASK.getStack(), BasicCraftItem.ADVANCED_SILICON_PLATE.getStack(), BasicCraftItem.ADVANCED_PROCESSOR_CHIP.getStack());
        addImprovedLithographyUnitRecipe(factory, BasicCraftItem.PERFECT_PROCESSOR_LITHOGRAPHIC_MASK.getStack(), BasicCraftItem.PERFECT_SILICON_PLATE.getStack(), BasicCraftItem.PERFECT_PROCESSOR_CHIP.getStack());

        addSolderingRecipe(factory, IC2Items.cable_copper, 2, IC2Items.dust_tin, 2, new ItemStack(Items.REDSTONE), 2, IC2Items.circuit);
        addSolderingRecipe(factory, IC2Items.circuit, 1, new ItemStack(Items.GLOWSTONE_DUST), 2, IC2Items.dust_lapis, 2, IC2Items.advanced_circuit);
        addSolderingRecipe(factory, BasicCraftItem.TEXTOLITE.getStack(), 1, IC2Items.dust_tin, 2, IC2Items.dust_gold, 2, BasicCraftItem.PROCESSOR_SUBSTRATE.getStack());
        addSolderingRecipe(factory, BasicCraftItem.TEXTOLITE.getStack(), 1, IC2Items.rubber, 2, BasicCraftItem.MICROCONTROLLER_CHIP.getStack(), 2, BasicCraftItem.MICROCONTROLLER.getStack());
        addSolderingRecipe(factory, BasicCraftItem.PROCESSOR_SUBSTRATE.getStack(), 1, IC2Items.circuit, 4, IC2Items.dust_tin, 4, BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack());
        addSolderingRecipe(factory, BasicCraftItem.PROCESSOR_SUBSTRATE.getStack(), 1, IC2Items.dust_tin, 1, BasicCraftItem.BASIC_PROCESSOR_CHIP.getStack(), 1, BasicCraftItem.BASIC_PROCESSOR.getStack());
        addSolderingRecipe(factory, BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack(), 1, IC2Items.advanced_circuit, 4, IC2Items.dust_gold, 4, BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack());
        addSolderingRecipe(factory, BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack(), 1, IC2Items.dust_gold, 2, BasicCraftItem.IMPROVED_PROCESSOR_CHIP.getStack(), 1, BasicCraftItem.IMPROVED_PROCESSOR.getStack());
        addSolderingRecipe(factory, BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(), 1, BasicCraftItem.MICROCONTROLLER.getStack(), 2, BasicCraftItem.MOLYBDENUM_DUST.getStack(), 2, BasicCraftItem.PERFECT_PROCESSOR_SUBSTRATE.getStack());
        addSolderingRecipe(factory, BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(), 1, IC2Items.dust_lead, 2, BasicCraftItem.ADVANCED_PROCESSOR_CHIP.getStack(), 1, BasicCraftItem.ADVANCED_PROCESSOR.getStack());
        addSolderingRecipe(factory, BasicCraftItem.PERFECT_PROCESSOR_SUBSTRATE.getStack(), 1,  BasicCraftItem.TECHNETIUM_DUST.getStack(), 2, BasicCraftItem.PERFECT_PROCESSOR_CHIP.getStack(), 1, BasicCraftItem.PERFECT_PROCESSOR.getStack());

        //        Recipes.electrolyzer.addRecipe("ic2heavy_water", 40, 1, new IElectrolyzerRecipeManager.ElectrolyzerOutput("itc_deuterium", 26, EnumFacing.UP), new IElectrolyzerRecipeManager.ElectrolyzerOutput("ic2oxygen", 13, EnumFacing.DOWN));
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

    private static void addCannerEnrichRecipe(FluidStack fluid, IRecipeInput additive, FluidStack output) {
        Recipes.cannerEnrich.addRecipe(new ICannerEnrichRecipeManager.Input(fluid, additive), output, null, false);
    }

    private static void addCannerBottleRecipe(IRecipeInput input, IRecipeInput fill, ItemStack output) {
        Recipes.cannerBottle.addRecipe(input, fill, output);
    }

    private static void addOreWashingRecipe(IRecipeInput input, ItemStack output, NBTTagCompound nbt) {
        Recipes.oreWashing.addRecipe(input, nbt, false, output);
    }

    private static void addExtrudingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerExtruding.addRecipe(input, null, false, output);
    }

    private static void addRollingRecipe(IRecipeInput input, ItemStack output) {
        Recipes.metalformerRolling.addRecipe(input, null, false, output);
    }

    private static void addBlastRecipe(IRecipeInput input, ItemStack output) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("duration", 200);
        nbt.setInteger("fluid", 1);
        Recipes.blastfurnace.addRecipe(input, nbt, false, output);
    }

    private static void addCentrifugeRecipe(IRecipeInput input, ItemStack... output) {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("minHeat", 600);
        Recipes.centrifuge.addRecipe(input, nbt, false, output);
    }

    private static void addLithographyUnitRecipe(IRecipeInputFactory factory, ItemStack mask, ItemStack plate, ItemStack chip) {
        if (chip.isEmpty()) return;
        ITcRecipes.lithography_unit.addRecipe(factory,
                new LithographyRecipeManager.LithographyRecipeInput(mask, true),
                new LithographyRecipeManager.LithographyRecipeInput(plate, false),
                chip);
    }

    private static void addImprovedLithographyUnitRecipe(IRecipeInputFactory factory, ItemStack mask, ItemStack plate, ItemStack chip) {
        if (chip.isEmpty()) return;
        ITcRecipes.improved_lithography_unit.addRecipe(factory,
                new LithographyRecipeManager.LithographyRecipeInput(mask, true),
                new LithographyRecipeManager.LithographyRecipeInput(plate, false),
                chip);
    }

    private static void addSolderingRecipe(IRecipeInputFactory factory, ItemStack firstInput, int firstCount, ItemStack secondInput, int secondCount, ItemStack tridInput, int tridCount, ItemStack output) {
        if (output.isEmpty()) return;
        ItemStack first = firstInput.copy();
        first.setCount(firstCount);
        ItemStack second = secondInput.copy();
        second.setCount(secondCount);
        ItemStack trid = tridInput.copy();
        trid.setCount(tridCount);
        ITcRecipes.industrial_soldering_station.addRecipe(factory, first, second, trid, output);
    }

    private static void addCrystalGrowerRecipe(IRecipeInput input, ItemStack output) {
        ITcRecipes.crystal_grower.addRecipe(input, null, false, output);
    }
}
