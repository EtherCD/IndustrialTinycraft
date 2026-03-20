package cd.ethercd.it;

import cd.ethercd.it.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ITcItemLoader {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final BasicOverclockerUpgrade improved_overclocker = new BasicOverclockerUpgrade("improved_overclocker");
    public static final BasicProcessingImproverUpgrade processing_improver = new BasicProcessingImproverUpgrade("processing_improver", 2, 2);
    public static final BasicProcessingImproverUpgrade advanced_processing_improver = new BasicProcessingImproverUpgrade("advanced_processing_improver", 3, 3);
    public static final BasicHeatStorage improved_hex_heat_storage = new BasicHeatStorage("improved_hex_heat_storage", 90000);
    public static final BasicHeatStorage improved_tri_heat_storage = new BasicHeatStorage("improved_tri_heat_storage", 45000);
    public static final BasicHeatStorage improved_heat_storage = new BasicHeatStorage("improved_heat_storage", 15000);
    public static final BasicHeatStorage advanced_hex_heat_storage = new BasicHeatStorage("advanced_hex_heat_storage", 120000);
    public static final BasicHeatStorage advanced_tri_heat_storage = new BasicHeatStorage("advanced_tri_heat_storage", 60000);

    public static void register() {
        BasicCraftItem.register();
    }
}
