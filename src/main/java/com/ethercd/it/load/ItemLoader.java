package com.ethercd.it.load;

import com.ethercd.it.items.BasicProcessingImproverUpgrade;
import com.ethercd.it.items.BasicOverclockerUpgrade;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemLoader {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final BasicOverclockerUpgrade improved_overclocker = new BasicOverclockerUpgrade("improved_overclocker");
    public static final BasicProcessingImproverUpgrade processing_improver = new BasicProcessingImproverUpgrade("processing_improver");
}
