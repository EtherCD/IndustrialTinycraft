package cd.ethercd.it.items;

import cd.ethercd.it.ITcFluid;
import cd.ethercd.it.ITcItemLoader;
import ic2.core.init.Localization;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

import javax.annotation.Nullable;
import java.util.List;

public class BasicEmptyBattery extends BasicItem {
    public BasicEmptyBattery(String name) {
        super(name);
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return new BatteryFluidHandler(stack);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(Localization.translate("industrialtinycraft.tooltip.unfilled_battery"));
    }

    private static class BatteryFluidHandler extends FluidHandlerItemStack {
        public BatteryFluidHandler(ItemStack container) {
            super(container, 1000);
        }

        @Override
        public boolean canFillFluidType(FluidStack fluid) {
            return fluid != null && fluid.containsFluid(ITcFluid.LITHIUM_ELECTROLYTE.getStack());
        }

        @Override
        public int fill(FluidStack resource, boolean doFill) {
            if (resource == null || !canFillFluidType(resource)) {
                return 0;
            }

            int filled = super.fill(resource, doFill);

            if (doFill && this.getFluid() != null && this.getFluid().amount >= this.capacity) {
                ItemStack filledBattery = new ItemStack(ITcItemLoader.lithium_battery);

                if (container.hasTagCompound()) {
                    filledBattery.setTagCompound(container.getTagCompound().copy());
                }

                this.container = filledBattery;
            }

            return filled;
        }

        @Override
        public FluidStack drain(int maxDrain, boolean doDrain) {
            return null;
        }

        @Override
        public FluidStack drain(FluidStack resource, boolean doDrain) {
            return null;
        }
    }
}
