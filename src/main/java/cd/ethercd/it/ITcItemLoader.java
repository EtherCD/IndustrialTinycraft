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

    public static final BasicEnrichmentFuel molybdenum_rod = new BasicEnrichmentFuel("molybdenum_rod", 1000, BasicCraftItem.DEPLETED_MOLYBDENUM.getStack());
    public static final BasicEnrichmentFuel molybdenum_dual_rod = new BasicEnrichmentFuel("molybdenum_dual_rod", 1000, BasicCraftItem.DEPLETED_DUAL_MOLYBDENUM.getStack());
    public static final BasicEnrichmentFuel molybdenum_quad_rod = new BasicEnrichmentFuel("molybdenum_quad_rod", 1000, BasicCraftItem.DEPLETED_QUAD_MOLYBDENUM.getStack());

    public static final BasicNeutronAbsorber hafnium_rod = new BasicNeutronAbsorber("hafnium_rod", 5000, -2F, 10);

    public static final BasicElectricStorage lithium_battery = new BasicElectricStorage("lithium_battery", 4000000, 4);
    public static final BasicElectricStorage lithium_battery_assembly = new BasicElectricStorage("lithium_battery_assembly", 4000000 * 9, 5);

    public static final BasicElectricStorage silicon_lithium_battery = new BasicElectricStorage("silicon_lithium_battery", (int) (4000000 * 1.5), 4);
    public static final BasicElectricStorage silicon_lithium_battery_assembly = new BasicElectricStorage("silicon_lithium_battery_assembly", (int) (4000000 * 1.5 * 9), 5);


    public static void register() {
        BasicCraftItem.register();
    }
}
