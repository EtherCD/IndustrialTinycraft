package com.ethercd.it.items;

import com.ethercd.it.ITcCreativeTab;
import com.ethercd.it.IndustrialTinyCraft;
import com.ethercd.it.load.ItemLoader;
import com.ethercd.it.utils.IHasModel;
import net.minecraft.item.Item;

public class BasicItem extends Item implements IHasModel {
    public BasicItem(String name) {
        setRegistryName(name);
        setCreativeTab(ITcCreativeTab.CREATIVE_TAB);
        ItemLoader.ITEMS.add(this);
        setTranslationKey(name);
    }

    @Override
    public void registerModels() {
        IndustrialTinyCraft.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
