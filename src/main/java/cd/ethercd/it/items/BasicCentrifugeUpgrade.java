package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.upgrade.IProcessingUpgrade;
import ic2.api.upgrade.IUpgradableBlock;
import ic2.api.upgrade.UpgradableProperty;
import ic2.core.block.machine.tileentity.TileEntityCentrifuge;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

public class BasicCentrifugeUpgrade extends BasicItem implements IProcessingUpgrade {
    public BasicCentrifugeUpgrade(String name) {
        super(name);
        ITcItemLoader.ITEMS.add(this);
    }

    @Override
    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    @Override
    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 0;
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
        if (parent instanceof TileEntityCentrifuge) {
            TileEntityCentrifuge machine = (TileEntityCentrifuge) parent;
            if (machine.useEnergy(1.0F))
                machine.heat += 2;
            if (machine.heat > machine.workheat) {
                machine.heat = machine.workheat;
            }
        }

        return false;
    }

    @Override
    @ParametersAreNonnullByDefault
    public Collection<ItemStack> onProcessEnd(ItemStack stack, IUpgradableBlock parent, Collection<ItemStack> output) {
        return output;
    }

    @SideOnly(Side.CLIENT)
    @Override
    @ParametersAreNonnullByDefault
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.centrifuge_upgrade").getFormattedText());
    }
}
