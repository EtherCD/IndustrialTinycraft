package com.ethercd.it.items;


import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.block.machine.tileentity.TileEntityMacerator;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import net.minecraft.item.ItemStack;

import java.util.*;

public class BasicProcessingImproverUpgrade extends BasicItem implements IProcessingUpgrade {
    private boolean needToConsume = false;
    private int oldStackSize = 0;

    public BasicProcessingImproverUpgrade(String name) {
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
        return 2.5;
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
        if (parent instanceof TileEntityStandardMachine && needToConsume) {
            TileEntityStandardMachine<?, ?, ?> machine = (TileEntityStandardMachine<?, ?, ?>) parent;
            int count = machine.inputSlot.get().getCount();
            if (count > 0) {
                int diff = oldStackSize - count;
                machine.inputSlot.consume(diff);
            }

            needToConsume = false;
        }
        return false;
    }

    @Override
    public Collection<ItemStack> onProcessEnd(ItemStack stack, IUpgradableBlock parent, Collection<ItemStack> output) {
        if (parent instanceof TileEntityStandardMachine) {
            List<ItemStack> out = new ArrayList<>();
            needToConsume = true;
            TileEntityStandardMachine<?, ?, ?> machine = (TileEntityStandardMachine<?, ?, ?>) parent;
            oldStackSize = machine.inputSlot.get().getCount();
            for (ItemStack item : output) {
                if (item.isStackable()) {
                    item.setCount(item.getCount() * 2);
                    out.add(item);
                } else {
                    out.add(item);
                }
            }
            return out;
        } else {
            return output;
        }
    }
}
