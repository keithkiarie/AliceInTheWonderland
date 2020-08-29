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
        Scanner sc = new Scanner(System.in);
        String text = sc.NextLine();

        if (text.isEmpty()) {
            return;
        }

        String tokens = text.split(" ");
        if (tokens[0].equalsIgnoreCase("drop")) {
            DropItem(tokens[1]);
            return;
        } else if (tokens[0].equalsIgnoreCase("map")) {
            DisplayMap();
            return;
        } else if(tokens[0].equalsIgnoreCase("instructions")) {
            PostInstructionsMessage();
            return;
        } else if(tokens[0].equalsIgnoreCase("inventory")) {
            ShowCollectedItems();
            return;
        }

        switch(possibleActions) {
        case AllowChangeOfLocation:
            if (!tokens[0].equalsIgnoreCase("go")) {
                System.out.println("You need to choose a place to go...");
                return GetUserInput(location, possibleActions);
            }

            break;

        case CollectItem:
            if (!tokens[0].equalsIgnoreCase("collect")) {
                System.out.println("You should collect something before you proceed...");
                return GetUserInput(location, possibleActions);
            }

            int x = Integer.parseInt(tokens[1]);
            Item it = location.Items.get(x);
            CollectItem(it);
            break;

        case GiveItem:
            if (!tokens[0].equalsIgnoreCase("give")) {
                System.out.println("Maybe you would want to give something?...");
                return GetUserInput(location, possibleActions);
            }
            int x = Integer.parseInt(tokens[1]);
            Item it = location.Items.get(x);
            if(Location.RabbitHouse == location.RabbitHouse) { // will be equal?
                GiveItem(item, Character.Rabbit, location);
                return;
            } else if(Location.MarchHaresHouse == location.MarchHaresHouse) {
                GiveItem(item, Character.MarchHaresHouse, location);
                return;
            }
            break;
        }


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
        Scanner sc = new Scanner(System.in);

        for (String Text: Texts) {
            System.out.println(Text);
            String UserInput = sc.nextLine();

            if (UserInput.compareToIgnoreCase("skip") == 0) return;

        }
    }

}
