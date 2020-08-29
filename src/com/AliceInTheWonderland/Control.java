package com.AliceInTheWonderland;


import java.util.ArrayList;
import java.util.Scanner;

enum PossibleActions {
    AllowChangeOfLocation,
    CollectItem,
    GiveItem
}

public class Control {

    // called from a location (in Actions). Takes in a list of items available in that room
    public static void GetUserInput(Location location, PossibleActions possibleActions) {
        
    }

    public static void DisplayCollectibleItems(Location location, int index, String Text) {

    }

    public static void PostWelcomeMessage() {
        Control.PrintDelayedTexts(Wonderland.IntroductionTexts);
    }

    public static void PostInstructionsMessage() {
        Control.PrintDelayedTexts(Wonderland.InstructionsTexts);
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

    public static boolean GiveItem(Item item, Character character, Location location) {
        if (HasItem(item)) {
            DropItem(item.Name);

            if (character == Character.Rabbit && location == character.location) {
                Wonderland.PaidTheRabbit = true;
            } else if (character == Character.MarchHare && location == character.location) {
                Wonderland.PaidTheMarchHare = true;
            }
            return true;
        }
        return false;
    }

    public static boolean HasItem(Item item) {

        for (Item i:Inventory.MyInventory()) {
            if (i.Name.compareToIgnoreCase(item.Name) == 0) {
                return true;
            }
        }
        return false;
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

    public static void GoToLocation(Location location) {
        switch (location.id) {
            case 1:
                Wonderland.CurrentLocation = Location.LongHall;
                Actions.LongHall();
                break;
            case 2:
                Wonderland.CurrentLocation = Location.Garden;
                Actions.Garden();
                break;
            case 3:
                Actions.Courtroom();
                Wonderland.CurrentLocation = Location.Courtroom;
                break;
            case 4:
                Wonderland.CurrentLocation = Location.MarchHaresHouse;
                Actions.MarchHaresHouse();
                break;
            case 5:
                Wonderland.CurrentLocation = Location.DuchessHouse;
                Actions.DuchessHouse();
                break;
            case 6:
                Wonderland.CurrentLocation = Location.CroquetPlayground;
                Actions.CroquetPlayground();
                break;
            case 7:
                Wonderland.CurrentLocation = Location.RabbitsHouse;
                Actions.RabbitsHouse();
                break;
            case 8:
                Wonderland.CurrentLocation = Location.Shores;
                Actions.Shores();
                break;
            case 9:
                Wonderland.CurrentLocation = Location.SafeRoom;
                Actions.SafeRoom();
                break;
        }
    }

    public static void PrintDelayedTexts(ArrayList<String> Texts) {
        Scanner sc = new Scanner(System.in);

        for (String Text: Texts) {
            System.out.println(Text);
            String UserInput = sc.nextLine();

            if (UserInput.compareToIgnoreCase("skip") == 0) return;

        }
    }

}
