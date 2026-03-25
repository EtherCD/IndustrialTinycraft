package cd.ethercd.it;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

import java.awt.*;

public enum ITcFluid  {
    LITHIUM_ELECTROLYTE("itc_lithium_electrolyte", "lithium_electrolyte_still", "lithium_electrolyte_flow", Color.YELLOW, Material.WATER),
    DEUTERIUM("itc_deuterium", "deuterium_still", "deuterium_still", Color.BLUE, Material.WATER),
    TRITIUM("itc_tritium", "tritium_still", "tritium_still", Color.YELLOW, Material.WATER),
    ;

    private final FluidLiquid fluid;
    private final FluidStack stack;
    private final BlockFluid block;
    private final String name;

    ITcFluid(String name, String still, String flowing, Color color, Material material) {
        this.name = name;
        this.fluid = new FluidLiquid(name, still, flowing, color);
        registerFluid(this.fluid);
        this.block = new BlockFluid(name, this.fluid, material);
        this.stack = new FluidStack(this.fluid, 1000);
    }

    public FluidLiquid getFluid() {
        return this.fluid;
    }

    public FluidStack getStack() {
        return this.stack;
    }

    public BlockFluid getBlock() {
        return this.block;
    }

    public String getName() {
        return this.name;
    }

    @SideOnly(Side.CLIENT)
    public static void registerFluidModels() {
        for (ITcFluid basic : ITcFluid.values()) {
            final String variant = "type=" + basic.name().toLowerCase();
            final ModelResourceLocation location =
                    new ModelResourceLocation(
                            new ResourceLocation(IndustrialTinyCraft.MODID, "fluid"),
                            variant
                    );

            IndustrialTinyCraft.LOGGER.log(Level.INFO, "Let's register state mappers and mesh definitions for fluids for " + variant);

            ModelLoader.setCustomStateMapper(basic.getBlock(), new StateMapperBase() {
                @Override
                protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                    return location;
                }
            });
        }
    }

    private static void registerFluid(FluidLiquid basic) {
        FluidRegistry.registerFluid(basic);
        FluidRegistry.addBucketForFluid(basic);
    }

    public static class FluidLiquid extends Fluid {
        public FluidLiquid(String fluidName, String still, String flowing, Color color) {
            super(
                    fluidName,
                    new ResourceLocation(IndustrialTinyCraft.MODID, "blocks/fluids/" + still),
                    new ResourceLocation(IndustrialTinyCraft.MODID, "blocks/fluids/" + flowing),
                    color
            );
        }
    }

    public static class BlockFluid extends BlockFluidClassic {
        public BlockFluid(String name, Fluid fluid, Material material) {
            super(fluid, material);
            setRegistryName(name);
            setTranslationKey(name);

            ITcBlocksLoader.BLOCKS.add(this);
//            ITcItemLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        }

        @Override
        public EnumBlockRenderType getRenderType(IBlockState state) {
            return EnumBlockRenderType.MODEL;
        }
    }
}
