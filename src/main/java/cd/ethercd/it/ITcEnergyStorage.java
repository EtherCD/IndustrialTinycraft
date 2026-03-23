package cd.ethercd.it;

import cd.ethercd.it.storages.LithiumMFSUTileEntity;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.Util;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Set;

public enum ITcEnergyStorage implements ITeBlock {
    lithium_mfsu(LithiumMFSUTileEntity.class, 0, EnumRarity.EPIC),
    ;

    private final Class<? extends TileEntityElectricBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    public TileEntityBlock te;

    public static final ResourceLocation LOCATION = new ResourceLocation("industrialtinycraft", "storages");

    ITcEnergyStorage(Class<? extends TileEntityElectricBlock> teClass, int itemMeta, EnumRarity rarity) {
        this.teClass = teClass;
        this.itemMeta = itemMeta;
        this.rarity = rarity;
        GameRegistry.registerTileEntity(teClass, IndustrialTinyCraft.MODID + ":" + this.getName());
    }

    @Override
    public ResourceLocation getIdentifier() {
        return LOCATION;
    }

    @Override
    public boolean hasItem() {
        return true;
    }

    @Nullable
    @Override
    public Class<? extends TileEntityElectricBlock> getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        return false;
    }

    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return Util.allFacings;
    }

    @Override
    public float getHardness() {
        return 20.0F;
    }

    @Override
    public float getExplosionResistance() {
        return 0;
    }

    @Override
    public TeBlock.HarvestTool getHarvestTool() {
        return TeBlock.HarvestTool.Axe;
    }

    @Override
    public TeBlock.DefaultDrop getDefaultDrop() {
        return TeBlock.DefaultDrop.Self;
    }

    @Override
    public EnumRarity getRarity() {
        return this.rarity;
    }

    @Override
    public boolean allowWrenchRotating() {
        return true;
    }

    @Nullable
    @Override
    public TileEntityBlock getDummyTe() {
        return this.te;
    }

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public int getId() {
        return this.itemMeta;
    }

    public String[] getRecipeCategory() {
        return new String[] {this.getName()};
    }

    public ItemStack getStack() {
        return this.getDummyTe().getBlockType().getItemStack(this);
    }

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if(mc != null && IndustrialTinyCraft.MODID.equals(mc.getModId())) {
            ITcEnergyStorage[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ITcEnergyStorage block = var1[var3];
                if(block.getTeClass() != null) {
                    try {
                        block.te = block.getTeClass().newInstance();
                    } catch (Exception var6) {
                        if(Util.inDev()) {
                            var6.printStackTrace();
                        }
                    }
                }
            }

        } else {
            throw new IllegalAccessError("Don\'t mess with this please.");
        }
    }
}
