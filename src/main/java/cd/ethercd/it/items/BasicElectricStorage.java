package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;

import javax.annotation.ParametersAreNonnullByDefault;

public class BasicElectricStorage extends BasicItem implements IElectricItem {
    private final int maxCharge;
    private final int tier;
    private final int transferLimit;

    public BasicElectricStorage(String name, int maxCharge, int tier) {
        super(name);
        this.maxCharge = maxCharge;
        this.tier = tier;
        this.transferLimit = (int) (32 * Math.pow(4, tier - 1));
        ITcItemLoader.ITEMS.add(this);
    }

    @Override
    public boolean canProvideEnergy(ItemStack stack) {
        return true;
    }

    @Override
    public double getMaxCharge(ItemStack stack) {
        return this.maxCharge;
    }

    @Override
    public int getTier(ItemStack stack) {
        return this.tier;
    }

    @Override
    public double getTransferLimit(ItemStack stack) {
        return this.transferLimit;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public double getDurabilityForDisplay(ItemStack stack) {
        return ((double) this.maxCharge - ElectricItem.manager.getCharge(stack)) / (double) this.maxCharge;
    }
}
