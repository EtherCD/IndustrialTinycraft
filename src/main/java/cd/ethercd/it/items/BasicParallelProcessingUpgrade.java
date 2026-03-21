package cd.ethercd.it.items;


import cd.ethercd.it.ITcItemLoader;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.MachineRecipeResult;
import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.init.Localization;
import ic2.core.util.StackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.*;

public class BasicParallelProcessingUpgrade extends BasicItem implements IProcessingUpgrade {
    private boolean needToConsume = false;
    private int oldStackSize = 0;
    private int consumeMultiplier;
    private int produceMultiplier;
    private Collection<ItemStack> extraOutput;

    public BasicParallelProcessingUpgrade(String name, int consumeMultiplier, int produceMultiplier) {
        super(name);
        this.consumeMultiplier = consumeMultiplier;
        this.produceMultiplier = produceMultiplier;
        ITcItemLoader.ITEMS.add(this);
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
        return 0;
    }

    @Override
    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 1.6;
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
        if (needToConsume && parent instanceof TileEntityStandardMachine) {
            TileEntityStandardMachine<?, ?, ?> machine = (TileEntityStandardMachine<?, ?, ?>) parent;
            InvSlotProcessable<?, ?, ?> input = machine.inputSlot;

            int currentCount = input.get().getCount();
            int diff = oldStackSize - currentCount; // сколько уже съела машина

            if (diff > 0) {
                int extraConsume = diff * (this.consumeMultiplier - 1);

                if (extraConsume > 0) {
                    int available = input.get().getCount();
                    input.consume(Math.min(available, extraConsume));
                }

                for (int i = 1; i < this.produceMultiplier; i++) {
                    machine.outputSlot.add(copyCollection(extraOutput));
                }
            }
            needToConsume = false;
            return true;
        }

        return false;
    }

    @Override
    public Collection<ItemStack> onProcessEnd(ItemStack stack, IUpgradableBlock parent, Collection<ItemStack> output) {
        if (parent instanceof TileEntityStandardMachine) {
            TileEntityStandardMachine<?, ?, ?> machine = (TileEntityStandardMachine<?, ?, ?>) parent;
            ItemStack slotItem = machine.inputSlot.get();
            oldStackSize = slotItem.getCount();
            boolean canBoost = oldStackSize >= this.consumeMultiplier;
            if (canBoost) {
                extraOutput = copyCollection(output);
                needToConsume = true;
            }
            return output;
        }
        return output;
    }

    private Collection<ItemStack> copyCollection(Collection<ItemStack> items) {
        List<ItemStack> copy = new ArrayList<>();
        for (ItemStack item : items) {
            copy.add(item.copy());
        }
        return copy;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.parallel_processing_upgrade").getFormattedText());
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.parallel_processing_upgrade.not_working").getFormattedText());
        tooltip.add(Localization.translate("ic2.tooltip.upgrade.overclocker.power", new Object[]{BasicOverclockerUpgrade.decimalformat.format((double)100.0F * Math.pow(this.getEnergyDemandMultiplier(stack, (IUpgradableBlock)null), (double) StackUtil.getSize(stack)))}));
    }
}
