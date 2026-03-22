package cd.ethercd.it;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.awt.*;

public enum ITcFluid {
    LITHIUM_ELECTROLYTE("lithium_electrolyte", "lithium_electrolyte_still", "lithium_electrolyte_flow", Color.YELLOW, Material.WATER)
    ;

    private final FluidLiquid fluid;
    private final BlockFluid block;
    private final String name;

    ITcFluid(String name, String still, String flowing, Color color, Material material) {
        this.name = name;
        this.fluid = new FluidLiquid(name, still, flowing, color);
        registerFluid(this.fluid);
        this.block = new BlockFluid(name, this.fluid, material);
    }

    public FluidLiquid getFluid() {
        return this.fluid;
    }

    public BlockFluid getBlock() {
        return this.block;
    }

    public String getName() {
        return this.name;
    }

    public static void registerFluidModels() {
        for (ITcFluid basic : ITcFluid.values()) {
            ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(basic.getBlock()), new ItemMeshDefinition() {
                @Override
                @MethodsReturnNonnullByDefault
                public ModelResourceLocation getModelLocation(ItemStack itemStack) {
                    return new ModelResourceLocation(IndustrialTinyCraft.MODID+":"+basic.getName(), "fluid");
                }
            });
            ModelLoader.setCustomStateMapper(basic.getBlock(), new StateMapperBase() {
                @Override
                @MethodsReturnNonnullByDefault
                protected ModelResourceLocation getModelResourceLocation(IBlockState iBlockState) {
                    return new ModelResourceLocation(IndustrialTinyCraft.MODID+":"+basic.getName(), "fluid");
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
            super(fluidName, new ResourceLocation(IndustrialTinyCraft.MODID+":blocks/"+still), new ResourceLocation(IndustrialTinyCraft.MODID+":blocks/"+flowing), color);
        }
    }

    public static class BlockFluid extends BlockFluidClassic {
        public BlockFluid(String name, Fluid fluid, Material material) {
            super(fluid, material);
            setRegistryName(name);
            setTranslationKey(name);

            ITcBlocksLoader.BLOCKS.add(this);
            ITcItemLoader.ITEMS.add(new ItemBlock(this).setRegistryName(name));
        }

        @Override
        public EnumBlockRenderType getRenderType(IBlockState state) {
            return EnumBlockRenderType.MODEL;
        }
    }
}
