package cd.ethercd.it;

import cd.ethercd.it.blocks.ITcResource;
import cd.ethercd.it.items.BasicCraftItem;
import cd.ethercd.it.items.BasicItem;
import cd.ethercd.it.utils.ProcessOptimizerRecipeManager;
import cd.ethercd.it.utils.ProcessorAssemblerRecipeManager;
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
    public static ProcessorAssemblerRecipeManager processor_assembler = new ProcessorAssemblerRecipeManager();
    public static IBasicMachineRecipeManager processor_assembler_ic2_plug = new BasicMachineRecipeManager();
    public static ProcessOptimizerRecipeManager processs_optimizer = new ProcessOptimizerRecipeManager();
    public static IBasicMachineRecipeManager processs_optimizer_ic2_plug = new BasicMachineRecipeManager();
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

    private static void addTriPlateCraft(ItemStack output, ItemStack plate1, ItemStack plate2, ItemStack plate3) {
        addBasicRecipe(output,
                " G ",
                " R ",
                " F ",
                'G', plate1,
                'R', plate2,
                'F', plate3);
        addBasicRecipe(output,
                "G  ",
                "R  ",
                "F  ",
                'G', plate1,
                'R', plate2,
                'F', plate3);
        addBasicRecipe(output,
                "  G",
                "  R",
                "  F",
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

        addTriPlateCraft(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack(), IC2Items.plate_gold, IC2Items.rubber, BasicCraftItem.FIBERGLASS.getStack());
        addTriPlateCraft(BasicCraftItem.RAW_LITHIUM_ASSEMBLY.getStack(), IC2Items.plate_copper, BasicCraftItem.FIBERGLASS.getStack(), BasicCraftItem.GRAPHITE_PLATE.getStack());
        addTriPlateCraft(BasicCraftItem.MOLYBDENUM_ALLOY_DUST.getStack(), BasicCraftItem.MOLYBDENUM_DUST.getStack(), IC2Items.dust_iron, IC2Items.dust_coal_fuel);

        addBasicRecipe(BasicCraftItem.LITHIUM_SULFURIC_MIXTURE.getStack(2),
                "SSS",
                "BBB",
                "SSS",
                'B', IC2Items.dust_lithium,
                'S', IC2Items.dust_sulfuric);

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
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_overclocker),
                "   ",
                "CCC",
                "PUP",
                'C', new ItemStack(ITcItemLoader.advanced_heat_storage),
                'P', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'U', new ItemStack(ITcItemLoader.improved_overclocker));
        // Machines
        addBasicRecipe(ITcMachine.crystal_grower.getStack(),
                "WSW",
                "BCB",
                "WSW",
                'W', IC2Items.cable_copper,
                'B', IC2Items.resource_machine,
                'S', IC2Items.advanced_circuit,
                'C', IC2Items.heat_storage);
        addBasicRecipe(ITcMachine.processor_assembler.getStack(),
                "BCB",
                "PGL",
                "BEB",
                'B', IC2Items.resource_advanced_machine,
                'C', IC2Items.advanced_circuit,
                'L', BasicCraftItem.SILICON_PLATE.getStack(),
                'G', IC2Items.reinfored_glass,
                'P', BasicCraftItem.LITHOGRAPHY_LASER.getStack(),
                'E', IC2Items.lapotron_crystal);
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
        addBasicRecipe(new ItemStack(ITcItemLoader.parallel_processing_upgrade),
                "TCT",
                "PMP",
                "TCT",
                'T', IC2Items.plate_tin,
                'C', IC2Items.tri_heat_storage,
                'P', BasicCraftItem.PROCESSOR_90NM.getItem(),
                'M', IC2Items.resource_machine);
        addBasicRecipe(new ItemStack(ITcItemLoader.advanced_parallel_processing_upgrade),
                "TCT",
                "PAP",
                "TCT",
                'T', IC2Items.plate_steel,
                'C', new ItemStack(ITcItemLoader.improved_tri_heat_storage),
                'P', BasicCraftItem.PROCESSOR_22NM.getItem(),
                'A', IC2Items.resource_advanced_machine);

        // Centrifuge Upgrade
        addBasicRecipe(new ItemStack(ITcItemLoader.always_on_centrifuge),
                "PCP",
                "MSM",
                "PCP",
                'P', IC2Items.plate_steel,
                'C', IC2Items.coil,
                'M', BasicCraftItem.PROCESSOR_90NM.getStack(),
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
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'L', new ItemStack(ITcItemLoader.lithium_battery_assembly),
                'M', IC2Items.mfsu);
        addBasicRecipe(ITcEnergyStorage.lithium_mfsu.getStack(),
                "PLP",
                "SMS",
                "PLP",
                'P', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'L', new ItemStack(ITcItemLoader.lithium_battery_assembly),
                'M', IC2Items.chargepad_mfsu);
        // Tech-Process Optimizer
        addBasicRecipe(ITcMachine.process_optimizer.getStack(),
                "PCP",
                "SBS",
                "PEP",
                'P', BasicCraftItem.MOLYBDENUM_ALLOY_PLATE.getStack(),
                'B', IC2Items.blast_furnace,
                'E', IC2Items.electric_heat_generator,
                'S', BasicCraftItem.PROCESSOR_90NM.getStack(),
                'C', IC2Items.energy_crystal);
        // Industrial Alloy Furance
        addBasicRecipe(ITcMachine.industrial_alloy_furnace.getStack(),
                "PCQ",
                "SBS",
                "QEP",
                'P', BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack(),
                'B', IC2Items.blast_furnace,
                'E', IC2Items.electric_heat_generator,
                'S', BasicCraftItem.PROCESSOR_22NM.getStack(),
                'C', new ItemStack(ITcItemLoader.lithium_battery),
                'Q', IC2Items.resource_advanced_machine);
    }

    public static void addMachineRecipes() {
        IRecipeInputFactory factory = Recipes.inputFactory;

        addCompressorRecipe(factory.forStack(BasicCraftItem.GLASS_DUST.getStack(), 4), BasicCraftItem.FIBERGLASS.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.RAW_PROCESSOR_SUBSTRATE.getStack()), BasicCraftItem.PROCESSOR_SUBSTRATE.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack(9)), BasicCraftItem.FIBER_OPTIC_PLATE.getStack());
        addCompressorRecipe(factory.forStack(IC2Items.dust_coal_fuel, 4), BasicCraftItem.GRAPHITE_PLATE.getStack());
        addCompressorRecipe(factory.forStack(BasicCraftItem.RAW_LITHIUM_ASSEMBLY.getStack()), BasicCraftItem.UNFILLED_LITHIUM_BATTERY.getStack());
        addCompressorRecipe(factory.forStack(new ItemStack(ITcItemLoader.lithium_battery), 9), new ItemStack(ITcItemLoader.lithium_battery_assembly));
        addCompressorRecipe(factory.forStack(BasicCraftItem.MOLYBDENUM_ALLOY_PLATE.getStack(), 9), BasicCraftItem.DENSE_MOLYBDENUM_ALLOY_PLATE.getStack());

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

        addCrystalGrowerRecipe(factory.forStack(BasicCraftItem.PURIFIED_SILICON.getStack()), BasicCraftItem.SILICON_INGOT.getStack());

        addProcessorAssemblerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_90NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(2), ItemStack.EMPTY, BasicCraftItem.PROCESSOR_90NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_90NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_90NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_45NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(2), ItemStack.EMPTY, BasicCraftItem.PROCESSOR_45NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.IMPROVED_PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_45NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_45NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_22NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(2), ItemStack.EMPTY, BasicCraftItem.PROCESSOR_22NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.PROCESSOR_22NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_22NM.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack(), BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack(), BasicCraftItem.PROCESSOR_7NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack(2), ItemStack.EMPTY, BasicCraftItem.PROCESSOR_7NM_CHIP.getStack());
        addProcessorAssemblerRecipe(factory, BasicCraftItem.PHOTONIC_COMPUTING_ACCELERATOR.getStack(), BasicCraftItem.PROCESSOR_7NM_CHIP.getStack(), BasicCraftItem.PROCESSOR_7NM.getStack());

        addProcessOptimizerRecipe(factory, BasicCraftItem.SILICON_PLATE.getStack(), BasicCraftItem.PURIFIED_REDSTONE.getStack(), ItemStack.EMPTY, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack());
        addProcessOptimizerRecipe(factory, BasicCraftItem.ALLOYED_SILICON_PLATE.getStack(), BasicCraftItem.PURIFIED_COPPER_DUST.getStack(), BasicCraftItem.MICROSTRUCTURED_FIBERGLASS_DUST.getStack(), BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack());
        addProcessOptimizerRecipe(factory, BasicCraftItem.MICROSTRUCTURED_SILICON_PLATE.getStack(), BasicCraftItem.HAFNIUM_DUST.getStack(), BasicCraftItem.PURIFIED_DIAMOND_DUST.getStack(), BasicCraftItem.HIGH_DENSITY_SILICON_PLATE.getStack());
        addProcessOptimizerRecipe(factory, BasicCraftItem.FIBER_OPTIC_PLATE.getStack(), BasicCraftItem.ADVANCED_PROCESSOR_SUBSTRATE.getStack(), BasicCraftItem.TECHNETIUM_DUST.getStack(), BasicCraftItem.PHOTONIC_COMPUTING_ACCELERATOR.getStack());

        Recipes.electrolyzer.addRecipe("ic2heavy_water", 40, 1, new IElectrolyzerRecipeManager.ElectrolyzerOutput("itc_deuterium", 26, EnumFacing.UP), new IElectrolyzerRecipeManager.ElectrolyzerOutput("ic2oxygen", 13, EnumFacing.DOWN));
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

    private static void addProcessorAssemblerRecipe(IRecipeInputFactory factory, ItemStack firstInput, ItemStack secondInput, ItemStack output) {
        if (output.isEmpty()) return;
        ITcRecipes.processor_assembler.addRecipe(firstInput, secondInput, output);
        if (!firstInput.isEmpty())
            ITcRecipes.processor_assembler_ic2_plug.addRecipe(factory.forStack(firstInput), null, false, output);
        if (!secondInput.isEmpty())
            ITcRecipes.processor_assembler_ic2_plug.addRecipe(factory.forStack(secondInput), null, false, output);
    }

    private static void addProcessOptimizerRecipe(IRecipeInputFactory factory, ItemStack firstInput, ItemStack secondInput, ItemStack tridInput, ItemStack output) {
        if (output.isEmpty()) return;
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
