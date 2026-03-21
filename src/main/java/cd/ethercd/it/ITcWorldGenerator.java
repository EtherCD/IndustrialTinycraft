package cd.ethercd.it;

import cd.ethercd.it.block.BasicCraftBlock;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class ITcWorldGenerator implements IWorldGenerator {
    private WorldGenerator cyrtolite_ore;

    public ITcWorldGenerator() {
        cyrtolite_ore = new WorldGenMinable(BasicCraftBlock.CYRTOLITE_ORE.getBlock().getDefaultState(), 4, BlockMatcher.forBlock(Blocks.STONE));
    }

    @Override
    public void generate(Random random, int chunkx, int chunkz, World world, IChunkGenerator iChunkGenerator, IChunkProvider iChunkProvider) {
        switch (world.provider.getDimension()) {
            case 0:
                for (int i = 0; i < 4; i++) {
                    int x = chunkx * 16 + random.nextInt(16);
                    int y = 10 + random.nextInt(80 - 20 + 1);
                    int z = chunkz * 16 + random.nextInt(16);

                    cyrtolite_ore.generate(world, random, new BlockPos(x,y,z));
                }
                break;
        }
    }

    public static void register() {
        GameRegistry.registerWorldGenerator(new ITcWorldGenerator(), 0);
    }
}
