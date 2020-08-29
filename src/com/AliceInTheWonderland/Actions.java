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

        System.out.println(location.Texts.get(0));

        location.Visited = true;

        Control.DisplayCollectibleItems(location);
        Control.GetUserInput(location);
    }

    public static void LongHall() {
        Location location = Location.LongHall;

        if (!location.Visited) System.out.println(location.Texts.get(0));
        else System.out.println(location.Texts.get(1));

        location.Visited = true;

        Control.DisplayCollectibleItems(location);
        Control.GetUserInput(location);
    }

    public static void DrinkInLongHall() {
        Location location = Location.LongHall;
        System.out.println(location.Texts.get(2));
    }

    public static void EatCakeInLongHall() {
        Location location = Location.LongHall;
        System.out.println(location.Texts.get(3));

        Wonderland.CriedAtLongHall = true;

        Wonderland.CurrentLocation = Location.Shores;

        Actions.Shores();
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

        Control.DisplayCollectibleItems(location);
        Control.GetUserInput(location);
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

        Control.DisplayCollectibleItems(location);
        Control.GetUserInput(location);
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

        Control.DisplayCollectibleItems(location);
        Control.GetUserInput(location);
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
