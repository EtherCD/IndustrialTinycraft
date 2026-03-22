package cd.ethercd.it;

import cd.ethercd.it.items.*;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ITcItemLoader {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final BasicOverclockerUpgrade improved_overclocker = new BasicOverclockerUpgrade("improved_overclocker", 0.4, 1.5);
    public static final BasicOverclockerUpgrade advanced_overclocker = new BasicOverclockerUpgrade("advanced_overclocker", 0.2, 1.4);
    public static final BasicCentrifugeUpgrade always_on_centrifuge = new BasicCentrifugeUpgrade("always_on_centrifuge");

    public static final BasicParallelProcessingUpgrade parallel_processing_upgrade = new BasicParallelProcessingUpgrade("parallel_processing_upgrade", 2, 2);
    public static final BasicParallelProcessingUpgrade advanced_parallel_processing_upgrade = new BasicParallelProcessingUpgrade("advanced_parallel_processing_upgrade", 3, 3);

    public static final BasicHeatStorage improved_hex_heat_storage = new BasicHeatStorage("improved_hex_heat_storage", 90000);
    public static final BasicHeatStorage improved_tri_heat_storage = new BasicHeatStorage("improved_tri_heat_storage", 45000);
    public static final BasicHeatStorage improved_heat_storage = new BasicHeatStorage("improved_heat_storage", 15000);
    public static final BasicHeatStorage advanced_hex_heat_storage = new BasicHeatStorage("advanced_hex_heat_storage", 120000);
    public static final BasicHeatStorage advanced_tri_heat_storage = new BasicHeatStorage("advanced_tri_heat_storage", 60000);
    public static final BasicHeatStorage advanced_heat_storage = new BasicHeatStorage("advanced_heat_storage", 15000);

    public static final BasicNeutronModerator neutron_moderator = new BasicNeutronModerator("neutron_moderator", 15000);

    public static final BasicRadioativeFuel technetium_rod = new BasicRadioativeFuel("technetium_rod", 5000, 1, 4F);
    public static final BasicRadioativeFuel technetium_dual_rod = new BasicRadioativeFuel("technetium_dual_rod", 5000, 2, 4F);
    public static final BasicRadioativeFuel technetium_quad_rod = new BasicRadioativeFuel("technetium_quad_rod", 5000, 4, 4F);

    public static final BasicEnrichmentFuel molybdenum_rod = new BasicEnrichmentFuel("molybdenum_rod", 1000, 1, 1F);
    public static final BasicEnrichmentFuel molybdenum_dual_rod = new BasicEnrichmentFuel("molybdenum_dual_rod", 1000, 2, 1F);
    public static final BasicEnrichmentFuel molybdenum_quad_rod = new BasicEnrichmentFuel("molybdenum_quad_rod", 1000, 4, 1F);

    public static void register() {
        BasicCraftItem.register();
    }
}
