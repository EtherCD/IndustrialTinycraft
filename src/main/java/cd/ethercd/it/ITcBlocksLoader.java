package cd.ethercd.it;

import cd.ethercd.it.blocks.BasicCraftBlock;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class ITcBlocksLoader {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static void register() {
        BasicCraftBlock.register();
    }
}
