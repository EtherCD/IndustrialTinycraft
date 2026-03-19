package cd.ethercd.it.items;


import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.Set;

public class BasicOverclockerUpgrade extends BasicItem implements IProcessingUpgrade {
    public BasicOverclockerUpgrade(String name) {
        super(name);
    }

    @Override
    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 0.5;
    }

    @Override
    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 0.4;
    }

    @Override
    public boolean isSuitableFor(ItemStack stack, Set<UpgradableProperty> types) {
        for (UpgradableProperty property : types) {
            if (property.equals(UpgradableProperty.Processing)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean onTick(ItemStack stack, IUpgradableBlock parent) {
        return false;
    }

    @Override
    public Collection<ItemStack> onProcessEnd(ItemStack stack, IUpgradableBlock parent, Collection<ItemStack> output) {
        return output;
    }
}
