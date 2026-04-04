package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public enum BasicCraftItem {
    CRUSHED_CYRTOLITE_ORE("crushed_cyrtolite_ore"),
    PURIFIED_CYRTOLITE_ORE("purified_cyrtolite_ore"),
    CRUSHED_WULFENITE_ORE("crushed_wulfenite"),
    PURIFIED_WULFENITE_ORE("purified_wulfenite"),
    CRUSHED_SKUTTERUDITE_ORE("crushed_skutterudite"),
    PURIFIED_SKUTTERUDITE_ORE("purified_skutterudite"),
    HAFNIUM_INGOT("hafnium_ingot"),
    HAFNIUM_DUST("hafnium_dust"),
    HAFNIUM_SMALL_DUST("hafnium_small"),
    HAFNIUM_PLATE("hafnium_plate"),
    DENSE_HAFNIUM_PLATE("dense_hafnium_plate"),

    ZIRCONIUM_INGOT("zirconium_ingot"),
    ZIRCONIUM_DUST("zirconium_dust"),
    ZIRCONIUM_SMALL_DUST("zirconium_small"),
    ZIRCONIUM_PLATE("zirconium_plate"),
    ZIRCONIUM_ROD("zirconium_fuel_rod"),

    TECHNETIUM_DUST("technetium_dust"),
    TECHNETIUM_SMALL_DUST("technetium_small"),

    ZIRCONIUM_TECHNETIUM_MIXTURE("zirconium_technetium_mixture"),

    COBALT_DUST("cobalt_dust"),
    COBALT_SMALL_DUST("cobalt_small"),
    COBALT_LITHIUM_MIXTURE("cobalt_lithium_mixture"),
    COBALT_LITHIUM_OXIDE_INGOT("cobalt_lithium_oxide_ingot"),
    COBALT_LITHIUM_OXIDE_PLATE("cobalt_lithium_oxide_plate"),

    MOLYBDENUM_DUST("molybdenum_dust"),
    MOLYBDENUM_SMALL_DUST("molybdenum_small"),

    PURIFIED_SILICON("purified_silicon"),
    PURIFIED_COPPER_DUST("purified_copper_dust"),
    PURIFIED_GOLD_DUST("purified_gold_dust"),
    PURIFIED_DIAMOND_DUST("purified_diamond_dust"),
    PURIFIED_REDSTONE("purified_redstone"),

    SILICON_INGOT("silicon_ingot"),
    SILICON_PLATE("silicon_plate"),

    IMPROVED_SILICON_PLATE("improved_silicon_plate"),
    ADVANCED_SILICON_PLATE("advanced_silicon_plate"),
    PERFECT_SILICON_PLATE("perfect_silicon_plate"),
    FIBER_OPTIC_PLATE("fiber_optic_plate"),

    GRAPHITE_PLATE("graphite_plate"),

    FIBERGLASS("fiberglass"),
    MICROSTRUCTURED_FIBERGLASS_DUST("microstructured_fiberglass_dust"),
    GLASS_DUST("glass_dust"),

    TEXTOLITE("textolite"),
    MICROCONTROLLER_CHIP("microcontroller_chip"),
    PROCESSOR_SUBSTRATE("processor_substrate"),
    IMPROVED_PROCESSOR_SUBSTRATE("improved_processor_substrate"),
    ADVANCED_PROCESSOR_SUBSTRATE("advanced_processor_substrate"),
    PERFECT_PROCESSOR_SUBSTRATE("perfect_processor_substrate"),

    MICROCONTROLLER("microcontroller"),
    BASIC_PROCESSOR("basic_processor"),
    IMPROVED_PROCESSOR("improved_processor"),
    ADVANCED_PROCESSOR("advanced_processor"),
    PERFECT_PROCESSOR("perfect_processor"),
    QUANTUM_PROCESSOR("quantum_processor"),
    PHOTONIC_PROCESSOR("photonic_processor"),

    MICROCONTROLLER_WAFFLE("microcontroller_waffle"),
    BASIC_PROCESSOR_WAFFLE("basic_processor_waffle"),
    IMPROVED_PROCESSOR_WAFFLE("improved_processor_waffle"),
    ADVANCED_PROCESSOR_WAFFLE("advanced_processor_waffle"),
    PERFECT_PROCESSOR_WAFFLE("perfect_processor_waffle"),

    BASIC_PROCESSOR_CHIP("basic_processor_chip"),
    IMPROVED_PROCESSOR_CHIP("improved_processor_chip"),
    ADVANCED_PROCESSOR_CHIP("advanced_processor_chip"),
    PERFECT_PROCESSOR_CHIP("perfect_processor_chip"),

    EMPTY_LITHOGRAPHIC_MASK("empty_lithographic_mask"),
    MICROCONTROLLER_LITHOGRAPHY_MASK("microcontroller_lithographic_mask"),
    BASIC_PROCESSOR_LITHOGRAPHIC_MASK("basic_processor_lithographic_mask"),
    IMPROVED_PROCESSOR_LITHOGRAPHIC_MASK("improved_processor_lithographic_mask"),
    ADVANCED_PROCESSOR_LITHOGRAPHIC_MASK("advanced_processor_lithographic_mask"),
    PERFECT_PROCESSOR_LITHOGRAPHIC_MASK("perfect_processor_lithographic_mask"),

    FUSION_CORE("fusion_core"),
    UNSTABLE_ENERGY_CORE("unstable_energy_core"),
    STABILIZED_ENERGY_CORE("stabilized_energy_core"),

    DEPLETED_TECHNETIUM("depleted_technetium", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_DUAL_TECHNETIUM("depleted_dual_technetium", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_QUAD_TECHNETIUM("depleted_quad_technetium", CustomConstructor.REACTOR_COMPONENT),

    DEPLETED_MOLYBDENUM("depleted_molybdenum", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_DUAL_MOLYBDENUM("depleted_dual_molybdenum", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_QUAD_MOLYBDENUM("depleted_quad_molybdenum", CustomConstructor.REACTOR_COMPONENT),

    DEPLETED_ZIRCONIUM_TECHNETIUM("depleted_zirconium_technetium", CustomConstructor.REACTOR_COMPONENT),

    TECHNETIUM_FUEL("technetium_fuel"),

    LITHIUM_SULFURIC_MIXTURE("lithium_sulfuric_mixture"),

    MOLYBDENUM_ALLOY_DUST("molybdenum_alloy_dust"),
    MOLYBDENUM_ALLOY_INGOT("molybdenum_alloy_ingot"),
    MOLYBDENUM_ALLOY_PLATE("molybdenum_alloy_plate"),
    DENSE_MOLYBDENUM_ALLOY_PLATE("dense_molybdenum_alloy_plate"),

    ;

    private BasicItem item;
    private final ItemStack stack;

    BasicCraftItem(String name) {
        this.item = new BasicItem(name);
        this.stack = new ItemStack(this.item);
    }

    BasicCraftItem(String name, CustomConstructor type) {
        if (Objects.requireNonNull(type) == CustomConstructor.REACTOR_COMPONENT) {
            this.item = new BasicReactorComponent(name);
        }
        if (Objects.requireNonNull(type) == CustomConstructor.EMPTY_BATTERY) {
            this.item = new BasicEmptyBattery(name);
        }
        assert this.item != null;
        this.stack = new ItemStack(this.item);
    }

    public BasicItem getItem() {
        return item;
    }

    public ItemStack getStack() {
        return stack;
    }

    public ItemStack getStack(int size) {
        ItemStack stack = new ItemStack(item);
        stack.setCount(size);
        return stack;
    }

    public static void register() {
        for (BasicCraftItem item : BasicCraftItem.values()) {
            ITcItemLoader.ITEMS.add(item.getItem());
        }
    }
    
    enum CustomConstructor {
        REACTOR_COMPONENT,
        EMPTY_BATTERY
    }
}
