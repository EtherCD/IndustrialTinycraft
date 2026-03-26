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

public class BasicEnrichmentFuel extends BasicItem implements IReactorComponent {
    private final ItemStack depleted;

    public BasicEnrichmentFuel(String name, int maxDamage, ItemStack depleted) {
        super(name);
        this.setMaxDamage(maxDamage);
        ITcItemLoader.ITEMS.add(this);
        this.depleted = depleted;
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {}

    protected ItemStack getDepletedStack(ItemStack stack, IReactor reactor) {
        return this.depleted.copy();
    }

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        {
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

    @Override
    @ParametersAreNonnullByDefault
    public boolean showDurabilityBar(ItemStack stack) {
        return true;
    }
}
