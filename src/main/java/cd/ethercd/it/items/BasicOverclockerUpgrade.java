package cd.ethercd.it.items;


import cd.ethercd.it.ITcItemLoader;
import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.init.Localization;
import ic2.core.util.StackUtil;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BasicOverclockerUpgrade extends BasicItem implements IProcessingUpgrade {
    public static final DecimalFormat decimalformat = new DecimalFormat("0.##");

    private final double processTimeMulti;
    private final double energyDemandMulti;

    public BasicOverclockerUpgrade(String name, double processTimeMulti, double energyDemandMult) {
        super(name);
        ITcItemLoader.ITEMS.add(this);
        this.processTimeMulti = processTimeMulti;
        this.energyDemandMulti = energyDemandMult;
    }

    @Override
    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return this.processTimeMulti;
    }

    @Override
    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return this.energyDemandMulti;
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

    @SideOnly(Side.CLIENT)
    @Override
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(Localization.translate("ic2.tooltip.upgrade.overclocker.time",decimalformat.format((double)100.0F * Math.pow(this.getProcessTimeMultiplier(stack, null), StackUtil.getSize(stack)))));
        tooltip.add(Localization.translate("ic2.tooltip.upgrade.overclocker.power", decimalformat.format((double)100.0F * Math.pow(this.getEnergyDemandMultiplier(stack, null), StackUtil.getSize(stack)))));
    }
}
