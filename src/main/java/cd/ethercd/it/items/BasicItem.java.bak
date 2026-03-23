package cd.ethercd.it.items;

import cd.ethercd.it.ITcCreativeTab;
import cd.ethercd.it.IndustrialTinyCraft;
import cd.ethercd.it.ITcItemLoader;
import cd.ethercd.it.utils.IHasModel;
import net.minecraft.item.Item;

public class BasicItem extends Item implements IHasModel {
    public BasicItem(String name) {
        setRegistryName(name);
        setCreativeTab(ITcCreativeTab.CREATIVE_TAB);
        setTranslationKey(name);
    }

    @Override
    public void registerModels() {
        IndustrialTinyCraft.proxy.registerItemRenderer(this, 0, "inventory");
    }
}
