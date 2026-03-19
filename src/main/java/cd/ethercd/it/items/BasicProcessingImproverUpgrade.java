package cd.ethercd.it.items;


import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import net.minecraft.item.ItemStack;

import java.util.*;

public class BasicProcessingImproverUpgrade extends BasicItem implements IProcessingUpgrade {
    private boolean needToConsume = false;
    private int oldStackSize = 0;
    private int consumeMultiplier;
    private int produceMultiplier;

    public BasicProcessingImproverUpgrade(String name, int consumeMultiplier, int produceMultiplier) {
        super(name);
        this.consumeMultiplier = consumeMultiplier;
        this.produceMultiplier = produceMultiplier;
    }

    @Override
    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 1;
    }

    @Override
    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 600;
    }

    @Override
    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 0;
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
            InvSlotProcessable<?, ?, ?> input = machine.inputSlot;
            int count = input.get().getCount();
            if (count > 0) {
                int diff = oldStackSize - count;
                int extra = diff * (this.consumeMultiplier - 1);

                if (input.get().getCount() >= extra) {
                    input.consume(extra);
                } else {
                    input.consume(input.get().getCount());
                }
                input.consume(diff );
            }

            needToConsume = false;
        }
        return false;
    }

    @Override
    public Collection<ItemStack> onProcessEnd(ItemStack stack, IUpgradableBlock parent, Collection<ItemStack> output) {
        if (parent instanceof TileEntityStandardMachine) {
            List<ItemStack> out = new ArrayList<>();
            TileEntityStandardMachine<?, ?, ?> machine = (TileEntityStandardMachine<?, ?, ?>) parent;
            ItemStack slotItem = machine.inputSlot.get();
            oldStackSize = slotItem.getCount();
            for (ItemStack item : output) {
                if (item.isStackable() && oldStackSize >= this.consumeMultiplier && oldStackSize + (item.getCount() * this.produceMultiplier) <= slotItem.getItem().getItemStackLimit()) {
                    item.setCount(item.getCount() * this.produceMultiplier);
                    needToConsume = true;
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
