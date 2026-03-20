package cd.ethercd.it.block;

import cd.ethercd.it.ITcBlocksLoader;
import cd.ethercd.it.ITcCreativeTab;
import cd.ethercd.it.ITcItemLoader;
import cd.ethercd.it.IndustrialTinyCraft;
import cd.ethercd.it.utils.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BasicBlock extends Block implements IHasModel {
    public BasicBlock(String name, Material material, SoundType sound, float hardness, float resistance) {
        super(material);
        setCreativeTab(ITcCreativeTab.CREATIVE_TAB);
        setSoundType(sound);
        setRegistryName(name);
        setTranslationKey(name);
        setHardness(hardness);
        setResistance(resistance);
    }

    @Override
    public void registerModels() {
        IndustrialTinyCraft.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}