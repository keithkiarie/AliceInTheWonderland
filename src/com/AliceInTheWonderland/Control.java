package com.AliceInTheWonderland;


import java.util.ArrayList;
import java.util.Scanner;

public class Control {

    // called from a location (in Actions). Takes in a list of items available in that room
    public static void GetUserInput(Location location, boolean AllowChangeOfLocation) {

    }

    public static void DisplayCollectibleItems(Location location, int index, String Text) {

    }

    public static void DisplayMap() {

        boolean HasMap = false;
        for (Item i:Inventory.MyInventory()) {
            if (i.Name.compareToIgnoreCase("map") == 0) HasMap = true;
        }

        if (!HasMap) {
            System.out.println("Damn it! I forgot to collect the map.");
            return;
        }
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
                if (i.Name.compareToIgnoreCase(name) == 0) {
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
        Scanner sc= new Scanner(System.in);

        for (String Text: Texts) {
            System.out.println(Text);
            String UserInput = sc.nextLine();

            if (UserInput.compareToIgnoreCase("skip") == 0) return;

        }
    }

}
