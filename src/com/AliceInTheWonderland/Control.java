package com.AliceInTheWonderland;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Control {

    // called from a location (in Actions). Takes in a list of items available in that room
    public static void GetUserInput(Location location) {

    }

    public static void DisplayCollectibleItems(Location location) {

    }

    public static void DisplayMap() {
        System.out.println("\nMap\n");
        System.out.println("Current location: " + Wonderland.CurrentLocation.Name);
        System.out.println("Current cardinal location: " + Wonderland.CurrentLocation.CardinalLocation);

        System.out.println("Long Hall: North");
        System.out.println("Garden: Northeast");
        System.out.println("Courtroom: East");
        System.out.println("March Hare's house: Southeast");
        System.out.println("Duchess' house: South");
        System.out.println("Croquet playground: Southwest");
        System.out.println("Rabbit's house: West");
        System.out.println("Shores: Northwest");

    }

    public static void CollectItem(Item item) {
        if (Inventory.AddItem(item)) System.out.println(item.Name + " collected. You now have " + Inventory.Count() + " items");
        else System.out.println("Could not collect item");

        if (Inventory.Count() == 3) System.out.println("You have 3 items. You do not have space for any more");
    }

    public static void DropItem(String name) {
        if (Inventory.Count() == 0) {
            System.out.println("You have no items in your inventory");
        } else {
            int index = 0;
            for (Item i:Inventory.MyInventory()) {
                if (i.Name.toUpperCase() == name.toUpperCase()) {
                    Inventory.DropItem(index);
                    return;
                }
                index++;
            }
        }

        System.out.println("You do not have a " + name);
    }

    public static void ShowCollectedItems() {
        if (Inventory.Count() == 0) System.out.println("0 collected items");
        else {
            System.out.println("Collected items:");
            for (Item i:Inventory.MyInventory()) {
                System.out.println("\t" + i.Name);
            }
        }
    }

    public static void GoToLocation(CardinalPoint location) {
        switch (location) {
            case North:
                Actions.LongHall();
                break;
            case Northeast:
                Actions.Garden();
                break;
            case East:
                Actions.Courtroom();
                break;
            case Southeast:
                Actions.MarchHaresHouse();
                break;
            case South:
                Actions.DuchessHouse();
                break;
            case Southwest:
                Actions.CroquetPlayground();
                break;
            case West:
                Actions.RabbitsHouse();
                break;
            case Northwest:
                Actions.Shores();
                break;
            case Central:
                Actions.SafeRoom();
                break;
        }
    }

    public static void PrintDelayedTexts(ArrayList<String> Texts) {
        for (String Text: Texts) {
            System.out.println(Text);
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
