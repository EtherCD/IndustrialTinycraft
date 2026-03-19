package cd.ethercd.it;

import cd.ethercd.it.items.BasicCraftItem;
import cd.ethercd.it.items.BasicItem;
import cd.ethercd.it.items.BasicProcessingImproverUpgrade;
import cd.ethercd.it.items.BasicOverclockerUpgrade;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ITcItemLoader {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final BasicOverclockerUpgrade improved_overclocker = new BasicOverclockerUpgrade("improved_overclocker");
    public static final BasicProcessingImproverUpgrade processing_improver = new BasicProcessingImproverUpgrade("processing_improver", 2, 2);
    public static final BasicProcessingImproverUpgrade advanced_processing_improver = new BasicProcessingImproverUpgrade("advanced_processing_improver", 3, 3);

    public static void register() {
        BasicCraftItem.register();
    }
}
