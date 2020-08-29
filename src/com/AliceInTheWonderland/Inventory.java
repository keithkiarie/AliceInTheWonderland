package com.AliceInTheWonderland;

import java.util.ArrayList;

public class Inventory {
    private static ArrayList<Item> CollectedItems = new ArrayList<Item>();

    public static int Count() {
        return CollectedItems.size();
    }

    public static void AddItem(Item item) {
        if (Inventory.Count() < 3) CollectedItems.add(item);
    }

    public static void DropItem(int index) {
        if (Inventory.Count() >= index) CollectedItems.remove(index);
    }

    public static ArrayList<Item> MyInventory() {
        return CollectedItems;
    }
}
