package com.ethercd.it;

import com.ethercd.it.items.BasicCraftItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

import static com.ethercd.it.IndustrialTinyCraft.MODID;

public class ITcCreativeTab {
    public static CreativeTabs CREATIVE_TAB = new CreativeTabs(MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(BasicCraftItem.PURIFIED_SILICON.getItem(), 1, 0);
        }
    };
}