package cd.ethercd.it;

import cd.ethercd.it.proxy.CommonProxy;
import ic2.api.event.TeBlockFinalCallEvent;
import ic2.core.block.TeBlockRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = IndustrialTinyCraft.MODID, name = IndustrialTinyCraft.NAME, version = IndustrialTinyCraft.VERSION)
public class IndustrialTinyCraft
{
    public static final String MODID = "industrialtinycraft";
    public static final String NAME = "Industrial TinyCraft";
    public static final String VERSION = "0.1";

    public static Logger LOGGER;

    @EventHandler
    public void start(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SidedProxy(clientSide = "cd.ethercd.it.proxy.ClientProxy", serverSide = "cd.ethercd.it.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("industrialtinycraft")
    public static IndustrialTinyCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ITcBlocksLoader.register();
        ITcItemLoader.register();
        LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ITcMachines.buildDummies();
        ITcRecipes.addBasicRecipes();
        ITcRecipes.addMachineRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @SubscribeEvent
    public void register(TeBlockFinalCallEvent event) {
        TeBlockRegistry.addAll(ITcMachines.class, ITcMachines.LOCATION);
    }
}
