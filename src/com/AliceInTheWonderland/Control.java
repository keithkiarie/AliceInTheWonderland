package com.AliceInTheWonderland;


import java.util.ArrayList;
import java.util.Scanner;

enum PossibleActions {
    AllowChangeOfLocation,
    CollectItem,
    GiveItem
}

public class Control {

    public static void GetUserInput(Location location, PossibleActions possibleActions) {
        if (possibleActions == PossibleActions.CollectItem) {

        }

        Scanner sc = new Scanner(System.in);
        String text = sc.nextLine();

        if (text.isEmpty() || text.equalsIgnoreCase("skip")) {
            if (possibleActions == PossibleActions.CollectItem) Wonderland.CurrentLocation.Items.remove(0);
            return;
        }


        // dropping an item
        String[] tokens = text.split(" ");
        if (tokens[0].equalsIgnoreCase("drop")) {

            if (tokens.length == 1) {
                System.out.println("\tTo drop an item, type 'drop' + the name of the item e.g. 'drop gem'");
            } else if (Inventory.Count() == 0) {
                System.out.println("\tYou do not have any item");
            } else {

                for (Item i : Inventory.MyInventory()) {
                    if (i.Name.equalsIgnoreCase(tokens[1])) DropItem(i.Name);
                    break;
                }
                System.out.println("\tYou do not have a " + tokens[1]);
                ShowCollectedItems();
            }

            GetUserInput(location, possibleActions);
            return;

        } else if (tokens[0].equalsIgnoreCase("map") ||
                (tokens.length == 2 && tokens[0].equalsIgnoreCase("show") && tokens[1].equalsIgnoreCase("map"))) {
            DisplayMap();
            GetUserInput(location, possibleActions);
            return;
        } else if (tokens[0].equalsIgnoreCase("instructions")) {
            PostInstructionsMessage();
            GetUserInput(location, possibleActions);
            return;
        } else if (tokens[0].equalsIgnoreCase("inventory") || tokens[0].equalsIgnoreCase("i")) {
            ShowCollectedItems();
            GetUserInput(location, possibleActions);
            return;
        }

        switch (possibleActions) {
            case AllowChangeOfLocation:
                if (!tokens[0].equalsIgnoreCase("go")) {
                    System.out.println("You need to choose a place to go...");
                    GetUserInput(location, possibleActions);
                    return;
                }

                CardinalPoint p = location.TextToCardinalPoint(tokens[1]);
                Location l = location.Exits.get(p);
                GoToLocation(l);
                break;

            case CollectItem:
                if (!tokens[0].equalsIgnoreCase("collect")) {
                    System.out.println("\tTo collect an item, type 'collect'");
                    GetUserInput(location, possibleActions);
                    return;
                }

                CollectItem();
                break;

            case GiveItem:
                if (!tokens[0].equalsIgnoreCase("give")) {
                    System.out.println("To give an item, type 'give' + the name of the item");
                    GetUserInput(location, possibleActions);
                    return;
                }
                int y = Integer.parseInt(tokens[1]);
                Item item = location.Items.get(y);
                if (Location.RabbitsHouse == location.RabbitsHouse) { // will be equal?
                    GiveItem(item, Character.Rabbit, location);
                } else if (Location.MarchHaresHouse == location.MarchHaresHouse) {
                    GiveItem(item, Character.MarchHare, location);
                }
                break;
        }
    }


    public static void ShowCollectibleItems(Location location, int index, String Text) {

    }

    public static void PostWelcomeMessage() {
        Control.PrintDelayedTexts(Wonderland.IntroductionTexts);
    }

    public static void PostInstructionsMessage() {
        Control.PrintDelayedTexts(Wonderland.InstructionsTexts);
    }

    public static void DisplayMap() {

        boolean HasMap = false;
        for (Item i : Inventory.MyInventory()) {
            if (i.Name.compareToIgnoreCase("map") == 0) HasMap = true;
        }

        if (!HasMap && Wonderland.CurrentLocation != Location.DeepWell) {
            System.out.println("\tDamn it! I forgot to collect the map.");
            return;
        } else if (!HasMap && Wonderland.CurrentLocation == Location.DeepWell) {
            System.out.println("\tWhere's my map! I thought I had carried one! Too bad!.");
            return;
        }
        System.out.println("\nMap\n");
        System.out.println("\tCurrent location: " + Wonderland.CurrentLocation.Name);
        System.out.println("\tCurrent cardinal location: " + Wonderland.CurrentLocation.CardinalLocation);

        System.out.println("\t\tLong Hall: North");
        System.out.println("\t\tGarden: Northeast");
        System.out.println("\t\tCourtroom: East");
        System.out.println("\t\tMarch Hare's house: Southeast");
        System.out.println("\t\tDuchess' house: South");
        System.out.println("\t\tCroquet playground: Southwest");
        System.out.println("\t\tRabbit's house: West");
        System.out.println("\t\tShores: Northwest");

    }

    public static boolean GiveItem(Item item, Character character, Location location) {
        if (Inventory.HasItem(item)) {
            DropItem(item.Name);

            if (character == Character.Rabbit && location == character.location) {
                Wonderland.PaidTheRabbit = true;
            } else if (character == Character.MarchHare && location == character.location) {
                Wonderland.PaidTheMarchHare = true;
            } else if (character == Character.Dodo && location == character.location) {
                Wonderland.PaidTheMarchHare = true;
            }
            return true;
        }
        return false;
    }


    public static void CollectItem() {

        Item item = Wonderland.CurrentLocation.Items.get(0);
        if (Inventory.AddItem(item)) System.out.println("\t" + item.Name + " collected.");
        else System.out.println("\tCould not collect item");

        Wonderland.CurrentLocation.Items.remove(0);

        if (Inventory.Count() == 3) System.out.println("\tYou have collected 1 item. Your pocket fits 3.");
        if (Inventory.Count() == 2)
            System.out.println("\tYou have 2 items in you pocket. Space for only one more! If you wish, you can drop an item.");
        if (Inventory.Count() == 3) System.out.println("\tYou have 3 items. You do not have space for any more");
    }

    public static void DropItem(String name) {

        int index = 0;
        for (Item i : Inventory.MyInventory()) {
            if (i.Name.compareToIgnoreCase(name) == 0) {
                Inventory.DropItem(index);
                System.out.println(i.Name + " dropped");
                return;
            }
            index++;
        }

    }

    public static void ShowCollectedItems() {
        if (Inventory.Count() == 0) System.out.println("\n\tYou have 0 collected items");
        else {
            System.out.println("\n\tCollected items:");
            for (Item i : Inventory.MyInventory()) {
                System.out.println("\t\t" + i.Name);
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

        for (String Text : Texts) {
            System.out.println(Text);
            String UserInput = sc.nextLine();

            if (UserInput.compareToIgnoreCase("skip") == 0) return;

        }
    }

}
