package cd.ethercd.it.items;

import net.minecraft.item.ItemStack;

public enum BasicCraftItem {
    CRUSHED_SILICON("crushed_silicon"),
    PURIFIED_SILICON("purified_silicon"),
    PURIFIED_COPPER_DUST("purified_copper_dust"),
    PURIFIED_GOLD_DUST("purified_gold_dust"),
    PURIFIED_DIAMOND_DUST("purified_diamond_dust"),

    BASIC_SEMICONDUCTOR_WAFFLE("basic_semiconductor_waffle"),

    FIBERGLASS("fiberglass"),
    GLASS_DUST("glass_dust"),
    RAW_PROCESSOR_SUBSTRATE("raw_processor_substrate"),
    PROCESSOR_SUBSTRATE("processor_substrate"),

    CACHE_MEMORY_CHIP("cache_memory_chip"),
    BASIC_PROCESSOR_CHIP("basic_processor_chip"),
    PROCESSOR_90NM("processor_90nm"),
    PROCESSOR_45NM("processor_45nm"),
    PROCESSOR_22NM("processor_22nm"),
    PROCESSOR_7NM("processor_7nm"),
    PROCESSOR_2NM("processor_2nm");

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
}
