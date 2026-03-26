package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import ic2.core.init.Localization;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class BasicNeutronModerator extends BasicItem implements IReactorComponent {
    public BasicNeutronModerator(String name, int max) {
        super(name);
        ITcItemLoader.ITEMS.add(this);
        this.setMaxDamage(max);
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {
    }

    public void acceptRay(ItemStack myStack, IReactor reactor, ItemStack pulsingStack,
                          int youX, int youY, int pulseX, int pulseY, float mult) {
        int dx = youX - pulseX;
        int dy = youY - pulseY;

        int nextX = youX + dx;
        int nextY = youY + dy;
        ItemStack nextItem = reactor.getItemAt(nextX, nextY);
        if (nextItem == null || nextItem.isEmpty()) return;
        IReactorComponent component = (IReactorComponent) nextItem.getItem();

        if (!(component instanceof BasicNeutronModerator)) {
            if (component.acceptUraniumPulse(pulsingStack, reactor, myStack, pulseX, pulseY, youX, youY, false))
                reactor.addOutput(mult);
        } else {
            ((BasicNeutronModerator) component).acceptRay(nextItem, reactor, myStack, nextX, nextY, youX, youY, mult + 4.0f);
        }
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack,
                                      int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        if (!heatrun) {
            int dx = youX - pulseX;
            int dy = youY - pulseY;

            int oppositeX = youX + dx;
            int oppositeY = youY + dy;

            ItemStack opposite = reactor.getItemAt(oppositeX, oppositeY);
            if (opposite != null && !opposite.isEmpty()) {
                IReactorComponent oppositeItem = (IReactorComponent) opposite.getItem();
                if (oppositeItem instanceof BasicNeutronModerator) {
                    ((BasicNeutronModerator) oppositeItem).acceptRay(opposite, reactor, stack, oppositeX, oppositeY, youX, youY, 4.0f);
                    return false;
                } else {
                    oppositeItem.acceptUraniumPulse(opposite, reactor, stack, oppositeX, oppositeY, youX, youY, false);
                }
            }
        } else {
            int damage = this.getDamage(stack) + 1;
            if (damage >= this.getMaxDamage(stack)) {
                reactor.setItemAt(youX, youY, ItemStack.EMPTY);
            } else {
                this.setDamage(stack, damage);
            }
        }

        return true;
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        return -1.0F;
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
    public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
        return true;
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.neutron_moderator_0").getFormattedText());
        tooltip.add(new TextComponentTranslation("industrialtinycraft.tooltip.neutron_moderator_1").getFormattedText());
        tooltip.add(Localization.translate("ic2.reactoritem.durability") + " " + (stack.getMaxDamage() - stack.getItemDamage()) + "/" + stack.getMaxDamage());
    }
}
