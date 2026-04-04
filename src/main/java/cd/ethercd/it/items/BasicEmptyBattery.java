package cd.ethercd.it.items;


public class BasicEmptyBattery extends BasicItem {
    public BasicEmptyBattery(String name) {
        super(name);
    }
//
//    @Override
//    @ParametersAreNonnullByDefault
//    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
//        return new BatteryFluidHandler(stack);
//    }
//
//    @Override
//    @ParametersAreNonnullByDefault
//    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//        tooltip.add(Localization.translate("industrialtinycraft.tooltip.unfilled_battery"));
//    }
//
//    private static class BatteryFluidHandler extends FluidHandlerItemStack {
//        public BatteryFluidHandler(ItemStack container) {
//            super(container, 1000);
//        }
//
//        @Override
//        public boolean canFillFluidType(FluidStack fluid) {
//            return fluid != null && fluid.containsFluid(ITcFluid.LITHIUM_ELECTROLYTE.getStack());
//        }
//
//        @Override
//        public int fill(FluidStack resource, boolean doFill) {
//            if (resource == null || !canFillFluidType(resource)) {
//                return 0;
//            }
//
//            int filled = super.fill(resource, doFill);
//
//            if (doFill && this.getFluid() != null && this.getFluid().amount >= this.capacity) {
//                ItemStack filledBattery = new ItemStack(ITcItemLoader.lithium_battery);
//
//                if (container.hasTagCompound()) {
//                    assert container.getTagCompound() != null;
//                    filledBattery.setTagCompound(container.getTagCompound().copy());
//                }
//
//                this.container = filledBattery;
//            }
//
//            return filled;
//        }
//
//        @Override
//        public FluidStack drain(int maxDrain, boolean doDrain) {
//            return null;
//        }
//
//        @Override
//        public FluidStack drain(FluidStack resource, boolean doDrain) {
//            return null;
//        }
//    }
}
