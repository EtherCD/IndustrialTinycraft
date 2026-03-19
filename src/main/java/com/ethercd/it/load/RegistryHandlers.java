package com.ethercd.it.load;

import com.ethercd.it.IndustrialTinyCraft;
import com.ethercd.it.items.BasicCraftItem;
import com.ethercd.it.utils.IHasModel;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandlers {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        IndustrialTinyCraft.LOGGER.info("To load items: ", BasicCraftItem.values().length);
        event.getRegistry().registerAll(ItemLoader.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ItemLoader.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }
    }
}
