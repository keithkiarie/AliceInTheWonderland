package com.AliceInTheWonderland;

import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class Actions {
    public static JSONObject ActionsJSON;


    public static void ReadActionsFile() throws IOException {
        String FileContents = "";

        try {
            FileReader fr = new FileReader("Actions.json");
            int i;
            while ((i = fr.read()) != -1)
                FileContents += (char) i;
            fr.close();
        } catch (IOException e) {
            throw e;
        }

        ActionsJSON = new JSONObject(FileContents);
    }

    Actions() {
        try {
            ReadActionsFile();
        } catch (IOException e) {
            System.out.println("Error accessing Actions file");
        }
    }


    public static void DeepWell() {
        Location location = Location.DeepWell;

        // introduction to deep well
        System.out.println(location.Texts.get(0));

        location.Visited = true;

        // she sees a jar
        Control.DisplayCollectibleItems(location, 0, location.Texts.get(1));
        Control.GetUserInput(location, PossibleActions.CollectItem);

        if (Inventory.HasItem(Item.Jar)) {
            Control.DisplayCollectibleItems(location, 1, location.Texts.get(2));
            Control.GetUserInput(location, PossibleActions.CollectItem);
        }

        // she sees map hanging on the wall
        Control.DisplayCollectibleItems(location, 2, location.Texts.get(3));
        Control.GetUserInput(location, PossibleActions.CollectItem);


        System.out.println(location.Texts.get(4));

        // she falls in the long hall
        Control.GoToLocation(Location.LongHall);
    }


    public static void LongHall() {
        Location location = Location.LongHall;

        // on entering
        if (!location.Visited) System.out.println(location.Texts.get(0));
        else System.out.println(location.Texts.get(1));

        location.Visited = true;

        // collect golden key
        Control.DisplayCollectibleItems(location, 0, location.Texts.get(2));

        // drink me bottle and eat me cake
        System.out.println(location.Texts.get(3));
        Control.GetUserInput(location, PossibleActions.CollectItem);
        location.Texts.get(4);
        Control.GetUserInput(location, PossibleActions.CollectItem);



    }

    public static void DrinkInLongHall() {
        Location location = Location.LongHall;
        System.out.println(location.Texts.get(5));

        if (Inventory.HasItem(Item.GoldenKey)) {
            // going through the small door
            System.out.println(location.Texts.get(5));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else if (!Inventory.HasItem(Item.GoldenKey)) {
            // she has to try the cake
            System.out.println(location.Texts.get(6));
            Control.GetUserInput(location, PossibleActions.CollectItem);
        }
    }

    public static void EatCakeInLongHall() {
        // she becomes so tall, she cries, then she shrinks
        Location location = Location.LongHall;
        System.out.println(location.Texts.get(6));

        Wonderland.CriedAtLongHall = true;

        Wonderland.AliceSize = Size.Short;
        Control.GoToLocation(Location.Shores);
    }

    public static void Garden() {
        Location location = Location.Garden;

        if (!location.Visited && !Location.Shores.Visited) {
            Wonderland.GamePlot = Plot.Duchess;
            System.out.println(location.Texts.get(0));
        } else {
            System.out.println(location.Texts.get(1));
        }

        location.Visited = true;

        Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
    }

    public static void Shores() {
        Location location = Location.Shores;

        if (!location.Visited && !Location.Garden.Visited) {
            Wonderland.GamePlot = Plot.Rabbit;
            System.out.println(location.Texts.get(0));
        } else if (location.Visited && Wonderland.CriedAtLongHall) {
            System.out.println(location.Texts.get(1));
        } else if (location.Visited && !Wonderland.CriedAtLongHall) {
            System.out.println(location.Texts.get(2));
        }

        location.Visited = true;

        Control.DisplayCollectibleItems(location, 0, "");
        Control.GetUserInput(location, PossibleActions.CollectItem);
    }

    public static void RabbitsHouse() {
        Location location = Location.RabbitsHouse;

        if (!location.Visited && Wonderland.GamePlot == Plot.Rabbit) {
            System.out.println(location.Texts.get(0));
        } else if (location.Visited && Wonderland.GamePlot == Plot.Rabbit && Wonderland.TimeIsTakenBack) {
            System.out.println(location.Texts.get(1));
        } else if (location.Visited && Wonderland.GamePlot == Plot.Rabbit && !Wonderland.TimeIsTakenBack) {
            System.out.println(location.Texts.get(2));
        } else if (Wonderland.GamePlot == Plot.Duchess) {
            System.out.println(location.Texts.get(3));
        }

        location.Visited = true;

        Control.DisplayCollectibleItems(location, 0, "");
        Control.GetUserInput(location, PossibleActions.CollectItem);
    }

    public static void MarchHaresHouse() {

    }

    public static void Courtroom() {

    }

    public static void DuchessHouse() {

    }

    public static void CroquetPlayground() {

    }

    public static void SafeRoom() {

    }
}
