package cd.ethercd.it.blocks;

import cd.ethercd.it.ITcBlocksLoader;
import cd.ethercd.it.ITcCreativeTab;
import cd.ethercd.it.ITcItemLoader;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public enum ITcResource {
    CYRTOLITE_ORE("cyrtolite_ore", Material.ROCK, SoundType.STONE, 5.0f, 8.0f),
    WULFENITE_ORE("wulfenite_ore", Material.ROCK, SoundType.STONE, 5.0f, 8.0f),
    PERFECT_MACHINE_CASING("perfect_machine", Material.IRON, SoundType.STONE, 5.0f, 8.0f)
    ;
    private final BasicBlock block;
    private final ItemBlock item;

    ITcResource(String name, Material material, SoundType soundType, float hardness, float resistance) {
        block = new BasicBlock(name, material, soundType, hardness, resistance);
        item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName()+"");
        item.setCreativeTab(ITcCreativeTab.CREATIVE_TAB);
    }

    public BasicBlock getBlock() {
        return block;
    }

    public Item getItem() {
        return item;
    }

    public ItemStack getStack() {
        return new ItemStack(item);
    }

    public static void register() {
        for (ITcResource basic : ITcResource.values()) {
            ITcBlocksLoader.BLOCKS.add(basic.getBlock());
            ITcItemLoader.ITEMS.add(basic.getItem());
        }
    }
}
