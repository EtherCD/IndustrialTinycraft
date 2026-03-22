package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.init.Localization;
import ic2.core.item.armor.ItemArmorHazmat;
import ic2.core.item.reactor.ItemReactorUranium;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BasicRadioativeFuel extends BasicItem implements IReactorComponent {
    private final int numberOfCells;
    private final float energyPulseMult;

    public BasicRadioativeFuel(String name, int maxDamage, int cells, float energyPulseMult) {
        super(name);
        this.setMaxDamage(maxDamage);
        ITcItemLoader.ITEMS.add(this);
        this.numberOfCells = cells;
        this.energyPulseMult = energyPulseMult;
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {
        if (reactor.produceEnergy()) {
            int basePulses = 1 + this.numberOfCells / 2;

            for(int iteration = 0; iteration < this.numberOfCells; iteration++) {
                int pulses = basePulses;
                if (!heatrun) {
                    for(int i = 0; i < pulses; i++) {
                        this.acceptUraniumPulse(stack, reactor, stack, x, y, x, y, heatrun);
                    }
                    int variableDontKnow = pulses + checkPulseable(reactor, x - 1, y, stack, x, y, heatrun) + checkPulseable(reactor, x + 1, y, stack, x, y, heatrun) + checkPulseable(reactor, x, y - 1, stack, x, y, heatrun) + checkPulseable(reactor, x, y + 1, stack, x, y, heatrun);
                } else {
                    pulses = basePulses + checkPulseable(reactor, x - 1, y, stack, x, y, heatrun) + checkPulseable(reactor, x + 1, y, stack, x, y, heatrun) + checkPulseable(reactor, x, y - 1, stack, x, y, heatrun) + checkPulseable(reactor, x, y + 1, stack, x, y, heatrun);
                    int heat = triangularNumber(pulses) * 4;
                    heat = this.getFinalHeat(stack, reactor, x, y, heat);
                    Queue<ItemStackCoord> heatAcceptors = new ArrayDeque<>();
                    this.checkHeatAcceptor(reactor, x - 1, y, heatAcceptors);
                    this.checkHeatAcceptor(reactor, x + 1, y, heatAcceptors);
                    this.checkHeatAcceptor(reactor, x, y - 1, heatAcceptors);
                    this.checkHeatAcceptor(reactor, x, y + 1, heatAcceptors);

                    while(!heatAcceptors.isEmpty() && heat > 0) {
                        int dheat = heat / heatAcceptors.size();
                        heat -= dheat;
                        ItemStackCoord acceptor = heatAcceptors.remove();
                        IReactorComponent acceptorComp = (IReactorComponent)acceptor.stack.getItem();
                        dheat = acceptorComp.alterHeat(acceptor.stack, reactor, acceptor.x, acceptor.y, dheat);
                        heat += dheat;
                    }

                    if (heat > 0) {
                        reactor.addHeat(heat);
                    }
                }
            }

            if (!heatrun && this.getDamage(stack) >= this.getMaxDamage(stack) - 1) {
                reactor.setItemAt(x, y, this.getDepletedStack(stack, reactor));
            } else if (!heatrun) {
                this.setDamage(stack, this.getDamage(stack) + 1);
            }
        }
    }

    protected int getFinalHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        return heat;
    }

    protected static int triangularNumber(int x) {
        return (x * x + x) / 2;
    }

    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        ItemStack ret;
        switch (this.numberOfCells) {
            case 1:
                ret = BasicCraftItem.DEPLETED_TECHNETIUM.getStack();
                break;
            case 2:
                ret = BasicCraftItem.DEPLETED_DUAL_TECHNETIUM.getStack();
                break;
            case 3:
            default:
                throw new RuntimeException("invalid cell count: " + this.numberOfCells);
            case 4:
                ret = BasicCraftItem.DEPLETED_QUAD_TECHNETIUM.getStack();
        }

        return ret.copy();
    }

    protected void checkHeatAcceptor(IReactor reactor, int x, int y, Queue<ItemStackCoord> heatAcceptors) {
        ItemStack stack = reactor.getItemAt(x, y);
        if (stack != null && stack.getItem() instanceof IReactorComponent && ((IReactorComponent)stack.getItem()).canStoreHeat(stack, reactor, x, y)) {
            heatAcceptors.add(new ItemStackCoord(stack, x, y));
        }
    }

    protected static int checkPulseable(IReactor reactor, int x, int y, ItemStack stack, int mex, int mey, boolean heatrun) {
        ItemStack other = reactor.getItemAt(x, y);
        return other != null && other.getItem() instanceof IReactorComponent && ((IReactorComponent)other.getItem()).acceptUraniumPulse(other, reactor, stack, x, y, mex, mey, heatrun) ? 1 : 0;
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            reactor.addOutput(this.energyPulseMult);
        }
        return true;
    }

    @Override
    public boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return false;
    }

    @Override
    public int getMaxHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return 0;
    }

    @Override
    public int getCurrentHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return 0;
    }

    @Override
    public int alterHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        return 0;
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        return 0;
    }

    @Override
    public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(Localization.translate("ic2.reactoritem.durability") + " " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (entity instanceof EntityLivingBase) {
            EntityLivingBase entityLiving = (EntityLivingBase)entity;
            if (!ItemArmorHazmat.hasCompleteHazmat(entityLiving)) {
                IC2Potion.radiation.applyTo(entityLiving, 200, 100);
            }
        }
    }

    private static class ItemStackCoord {
        public final ItemStack stack;
        public final int x;
        public final int y;

        public ItemStackCoord(ItemStack stack, int x, int y) {
            this.stack = stack;
            this.x = x;
            this.y = y;
        }
    }
}
