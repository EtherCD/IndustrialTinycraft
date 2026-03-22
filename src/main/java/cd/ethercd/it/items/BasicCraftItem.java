package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import net.minecraft.item.ItemStack;

import java.util.Objects;

public enum BasicCraftItem {
    CRUSHED_CYRTOLITE_ORE("crushed_cyrtolite_ore"),
    PURIFIED_CYRTOLITE_ORE("purified_cyrtolite_ore"),
    HAFNIUM_INGOT("hafnium_ingot"),
    HAFNIUM_DUST("hafnium_dust"),
    HAFNIUM_SMALL_DUST("hafnium_small"),
    HAFNIUM_PLATE("hafnium_plate"),

    ZIRCONIUM_INGOT("zirconium_ingot"),
    ZIRCONIUM_DUST("zirconium_dust"),
    ZIRCONIUM_SMALL_DUST("zirconium_small"),
    ZIRCONIUM_PLATE("zirconium_plate"),

    TECHNETIUM_DUST("technetium_dust"),
    TECHNETIUM_SMALL_DUST("technetium_small"),
    MOLYBDENUM_DUST("molybdenum_dust"),
    MOLYBDENUM_SMALL_DUST("molybdenum_small"),

    PURIFIED_SILICON("purified_silicon"),
    PURIFIED_COPPER_DUST("purified_copper_dust"),
    PURIFIED_GOLD_DUST("purified_gold_dust"),
    PURIFIED_DIAMOND_DUST("purified_diamond_dust"),
    PURIFIED_REDSTONE("purified_redstone"),

    SILICON_INGOT("silicon_ingot"),
    SILICON_PLATE("silicon_plate"),
    ALLOYED_SILICON_PLATE("alloyed_silicon_plate"),
    MICROSTRUCTURED_SILICON_PLATE("microstructured_silicon_plate"),
    HIGH_DENSITY_SILICON_PLATE("high_density_silicon_plate"),

    FIBERGLASS("fiberglass"),
    MICROSTRUCTURED_FIBERGLASS_DUST("microstructured_fiberglass_dust"),
    GLASS_DUST("glass_dust"),
    RAW_PROCESSOR_SUBSTRATE("raw_processor_substrate"),
    PROCESSOR_SUBSTRATE("processor_substrate"),
    IMPROVED_PROCESSOR_SUBSTRATE("improved_processor_substrate"),
    ADVANCED_PROCESSOR_SUBSTRATE("advanced_processor_substrate"),

    PROCESSOR_90NM("processor_90nm"),
    PROCESSOR_45NM("processor_45nm"),
    PROCESSOR_22NM("processor_22nm"),
    PROCESSOR_7NM("processor_7nm"),
    PROCESSOR_2NM("processor_2nm"),

    PROCESSOR_90NM_CHIP("processor_90nm_chip"),
    PROCESSOR_45NM_CHIP("processor_45nm_chip"),
    PROCESSOR_22NM_CHIP("processor_22nm_chip"),
    PROCESSOR_7NM_CHIP("processor_7nm_chip"),

    PHOTONIC_COMPUTING_ACCELERATOR("photonic_computing_accelerator"),

    LITHOGRAPHY_LASER("lithography_laser"),
    UNSTABLE_ENERGY_CORE("unstable_energy_core"),
    STABILIZED_ENERGY_CORE("stabilized_energy_core"),

    DEPLETED_TECHNETIUM("depleted_technetium", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_DUAL_TECHNETIUM("depleted_dual_technetium", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_QUAD_TECHNETIUM("depleted_quad_technetium", CustomConstructor.REACTOR_COMPONENT),

    DEPLETED_MOLYBDENUM("depleted_molybdenum", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_DUAL_MOLYBDENUM("depleted_dual_molybdanum", CustomConstructor.REACTOR_COMPONENT),
    DEPLETED_QUAD_MOLYBDENUM("depleted_quad_molybdenum", CustomConstructor.REACTOR_COMPONENT),

    TECHNETIUM_FUEL("technetium_fuel"),
    ;

    private BasicItem item;
    private ItemStack stack;

    BasicCraftItem(String name) {
        this.item = new BasicItem(name);
        this.stack = new ItemStack(this.item);
    }

    BasicCraftItem(String name, CustomConstructor type) {
        if (Objects.requireNonNull(type) == CustomConstructor.REACTOR_COMPONENT) {
            this.item = new BasicReactorComponent(name);
        }
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
        REACTOR_COMPONENT
        ;
    }
}
