package cd.ethercd.it.items;

import cd.ethercd.it.ITcItemLoader;
import ic2.api.reactor.IReactor;
import ic2.api.reactor.IReactorComponent;
import net.minecraft.item.ItemStack;

public class BasicHeatStorage extends BasicItem implements IReactorComponent {
    public BasicHeatStorage(String name, int heatStorage) {
        super(name);
        setMaxStackSize(1);
        setMaxDamage(heatStorage);
        ITcItemLoader.ITEMS.add(this);
    }

    @Override
    public void processChamber(ItemStack stack, IReactor reactor, int x, int y, boolean heatrun) {}

    @Override
    public boolean acceptUraniumPulse(ItemStack stack, IReactor reactor, ItemStack pulsingStack, int youX, int youY, int pulseX, int pulseY, boolean heatrun) {
        return false;
    }

    @Override
    public boolean canStoreHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return true;
    }

    @Override
    public int getMaxHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return stack.getMaxDamage();
    }

    @Override
    public int getCurrentHeat(ItemStack stack, IReactor reactor, int x, int y) {
        return stack.getItemDamage();
    }

    @Override
    public int alterHeat(ItemStack stack, IReactor reactor, int x, int y, int heat) {
        int myHeat = this.getCurrentHeat(stack, reactor, x, y);
        myHeat += heat;
        int max = this.getMaxHeat(stack, reactor, x, y);
        if (myHeat > max) {
            reactor.setItemAt(x, y, null);
            heat = max - myHeat + 1;
        } else {
            if (myHeat < 0) {
                heat = myHeat;
                myHeat = 0;
            } else {
                heat = 0;
            }

            stack.setItemDamage(myHeat);
        }

        return heat;
    }

    @Override
    public float influenceExplosion(ItemStack stack, IReactor reactor) {
        return 0;
    }

    @Override
    public boolean canBePlacedIn(ItemStack stack, IReactor reactor) {
        return true;
    }
}
