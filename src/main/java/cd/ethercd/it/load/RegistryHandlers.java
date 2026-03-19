package cd.ethercd.it.load;

import cd.ethercd.it.ITcItemLoader;
import cd.ethercd.it.IndustrialTinyCraft;
import cd.ethercd.it.items.BasicCraftItem;
import cd.ethercd.it.utils.IHasModel;
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
        event.getRegistry().registerAll(ITcItemLoader.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for (Item item : ITcItemLoader.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }
    }
}
