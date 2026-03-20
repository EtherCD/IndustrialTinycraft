package cd.ethercd.it;

import ic2.core.block.ITeBlock;
import ic2.core.block.TileEntityBlock;
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

public enum ITcMachines implements ITeBlock {
    crystal_grower(cd.ethercd.it.machines.CrystalGrowerTileEntity.class, 0, EnumRarity.UNCOMMON),
    processor_assembler(cd.ethercd.it.machines.ProcessorAssemblerTileEntity.class, 1, EnumRarity.UNCOMMON),
    process_optimizer(cd.ethercd.it.machines.ProcessOptimizerTileEntity.class, 2, EnumRarity.UNCOMMON),
    ;

    private final Class<? extends TileEntityBlock> teClass;
    private final int itemMeta;
    private final EnumRarity rarity;
    private TileEntityBlock te;

    public static final ResourceLocation LOCATION = new ResourceLocation("industrialtinycraft", "machines");

    ITcMachines(Class<? extends TileEntityBlock> teClass, int itemMeta, EnumRarity rarity) {
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
        return false;
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

    public static void buildDummies() {
        ModContainer mc = Loader.instance().activeModContainer();
        if(mc != null && IndustrialTinyCraft.MODID.equals(mc.getModId())) {
            ITcMachines[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                ITcMachines block = var1[var3];
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
