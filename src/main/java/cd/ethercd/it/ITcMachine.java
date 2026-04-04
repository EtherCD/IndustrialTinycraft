package cd.ethercd.it;

import cd.ethercd.it.machines.ImprovedLithographyUnitTileEntity;
import cd.ethercd.it.machines.IndustrialSolderingStationTileEntity;
import cd.ethercd.it.machines.LithographyUnitTileEntity;
import ic2.api.tile.IEnergyStorage;
import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
import ic2.core.ref.TeBlock;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nullable;
import java.util.Set;

public enum ITcMachine implements ITeBlock {
    crystal_grower(cd.ethercd.it.machines.CrystalGrowerTileEntity.class, 0, EnumRarity.UNCOMMON),
    lithography_unit(LithographyUnitTileEntity.class, 1, EnumRarity.UNCOMMON),
    improved_lithography_unit(ImprovedLithographyUnitTileEntity.class, 2, EnumRarity.EPIC),
    industrial_soldering_station(IndustrialSolderingStationTileEntity.class, 3, EnumRarity.UNCOMMON),
    industrial_alloy_furnace(cd.ethercd.it.machines.IndustrialAlloyFurnaceTileEntity.class, 4, EnumRarity.EPIC)
    ;

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    TileEntityBlock te;

    public static final ResourceLocation LOCATION = new ResourceLocation("industrialtinycraft", "machines");

    ITcMachine(Class<? extends TileEntityBlock> teClass, int itemMeta, EnumRarity rarity) {
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
    public Class getTeClass() {
        return this.teClass;
    }

    @Override
    public boolean hasActive() {
        return true;
    }

    @Override
    public Set<EnumFacing> getSupportedFacings() {
        return Util.horizontalFacings;
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

    public static ITeBlockCreativeRegisterer getCreativeRegisterer() {
        return (list, block, itemBlockTileEntity, tab) -> {
            if (tab == ITcCreativeTab.CREATIVE_TAB || tab == CreativeTabs.SEARCH) {
                for(ITeBlock type : values()) {
                    if (type.hasItem()) {
                        list.add(block.getItemStack(type));
                        if (type.getDummyTe() instanceof IEnergyStorage) {
                            ItemStack filled = block.getItemStack(type);
                            StackUtil.getOrCreateNbtData(filled).setDouble("energy", (double)((IEnergyStorage)type.getDummyTe()).getCapacity());
                            list.add(filled);
                        }
                    }
                }
            }
        };
    }

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if(mc != null && IndustrialTinyCraft.MODID.equals(mc.getModId())) {
            ITcMachine[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ITcMachine block = var1[var3];
                if(block.teClass != null) {
                    try {
                        block.te = block.teClass.newInstance();
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
