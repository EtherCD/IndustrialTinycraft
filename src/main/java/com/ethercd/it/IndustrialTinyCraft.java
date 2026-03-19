package com.ethercd.it;

import com.ethercd.it.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = IndustrialTinyCraft.MODID, name = IndustrialTinyCraft.NAME, version = IndustrialTinyCraft.VERSION)
public class IndustrialTinyCraft
{
    public static final String MODID = "industrialtinycraft";
    public static final String NAME = "Industrial TinyCraft";
    public static final String VERSION = "0.1";

    public static Logger LOGGER;

    @SidedProxy(clientSide = "com.ethercd.it.proxy.ClientProxy", serverSide = "com.ethercd.it.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.Instance("industrialtinycraft")
    public static IndustrialTinyCraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        LOGGER = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Crafting.addBasicRecipes();
        Crafting.addMachineRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
