package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import net.minecraft.item.ItemStack;

public enum BasicCraftItem {
    PURIFIED_SILICON("purified_silicon"),
    PURIFIED_COPPER_DUST("purified_copper_dust"),
    PURIFIED_GOLD_DUST("purified_gold_dust"),
    PURIFIED_DIAMOND_DUST("purified_diamond_dust"),
    PURIFIED_REDSTONE("purified_redstone"),

    SILICON_INGOT("silicon_ingot"),
    SILICON_PLATE("silicon_plate"),
    ALLOYED_SILICON_PLATE("alloyed_silicon_plate"),
    MICROSTRUCTURED_SILICON_PLATE("microstructured_silicon_plate"),

    FIBERGLASS("fiberglass"),
    MICROSTRUCTURED_FIBERGLASS_DUST("microstructured_fiberglass_dust"),
    GLASS_DUST("glass_dust"),
    RAW_PROCESSOR_SUBSTRATE("raw_processor_substrate"),
    PROCESSOR_SUBSTRATE("processor_substrate"),
    IMPROVED_PROCESSOR_SUBSTRATE("improved_processor_substrate"),
    ADVANCED_PROCESSOR_SUBSTRATE("advanced_processor_substrate"),

    CACHE_MEMORY_CHIP("cache_memory_chip"),
    PROCESSOR_90NM("processor_90nm"),
    PROCESSOR_45NM("processor_45nm"),
    PROCESSOR_22NM("processor_22nm"),
    PROCESSOR_7NM("processor_7nm"),
    PROCESSOR_2NM("processor_2nm"),

    PROCESSOR_90NM_CHIP("processor_90nm_chip"),
    PROCESSOR_45NM_CHIP("processor_45nm_chip"),
    PROCESSOR_22NM_CHIP("processor_22nm_chip"),

    LITHOGRAPHY_LASER("lithography_laser"),
    ;

    private BasicItem item;
    private ItemStack stack;

    BasicCraftItem(String name) {
        this.item = new BasicItem(name);
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
}
