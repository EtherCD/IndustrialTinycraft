package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.IC2Potion;
import ic2.core.init.Localization;
import ic2.core.item.armor.ItemArmorHazmat;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BasicEnrichmentFuel extends BasicItem implements IReactorComponent {
    private int numberOfCells;
    private float energyPulseMult;

    public BasicEnrichmentFuel(String name, int maxDamage, int cells, float energyPulseMult) {
        super(name);
        this.setMaxDamage(maxDamage);
        ITcItemLoader.ITEMS.add(this);
        this.numberOfCells = cells;
        this.energyPulseMult = energyPulseMult;
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {}

    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        ItemStack ret;
        switch (this.numberOfCells) {
            case 1:
                ret = BasicCraftItem.DEPLETED_MOLYBDENUM.getStack();
                break;
            case 2:
                ret = BasicCraftItem.DEPLETED_DUAL_MOLYBDENUM.getStack();
                break;
            case 3:
            default:
                throw new RuntimeException("invalid cell count: " + this.numberOfCells);
            case 4:
                ret = BasicCraftItem.DEPLETED_QUAD_MOLYBDENUM.getStack();
        }

        return ret.copy();
    }
    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            IReactorComponent source = (IReactorComponent) pulsingStack.getItem();
            source.acceptUraniumPulse(pulsingStack, reactor, stack, pulseX, pulseY, youX, youY, false);

            int dx = youX - pulseX;
            int dy = youY - pulseY;

            int oppositeX = youX + dx;
            int oppositeY = youY + dy;

            ItemStack opposite = reactor.getItemAt(oppositeX, oppositeY);

            if (!opposite.isEmpty() && opposite.getItem() instanceof IReactorComponent) {
                reactor.addOutput(2);
            } else if (!opposite.isEmpty() && opposite.getItem() instanceof BasicNeutronModerator) {
                ((BasicNeutronModerator)opposite.getItem()).acceptUraniumPulse(opposite, reactor, stack, oppositeX, oppositeY, youX, youY, heatrun);
            }
        } else {
            int damage = this.getDamage(stack) + 1;
            if (damage >= this.getMaxDamage(stack)) {
                reactor.setItemAt(youX, youY, getDepletedStack(stack, reactor));
            } else {
                this.setDamage(stack, damage);
            }
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
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.enrichment_fuel").getFormattedText());
        tooltip.add(Localization.translate("ic2.reactoritem.durability") + " " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
    }
}
