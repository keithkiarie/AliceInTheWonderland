package com.AliceInTheWonderland;

import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

// Plot is influenced by who is met first between the Gardeners and the Rabbit, determining who will help you
enum Plot {
    Duchess, Rabbit
}

public class Wonderland {

    public static Plot GamePlot;
    public static boolean CriedAtLongHall = false;
    public static boolean PaidTheRabbit = false;
    public static boolean TimeIsTakenBack = false;

    public ArrayList<String>IntroductionTexts = new ArrayList<String>();

    public static Location CurrentLocation;

    public static int Plot = 1;

    public static void main(String[] args) {
        Wonderland GameSession = new Wonderland();
    }

    Wonderland() {
        ConstructLocations();
        ReadMetaTexts();

        PostWelcomeMessage();
    }

    void PostWelcomeMessage() {
        Control.PrintDelayedTexts(this.IntroductionTexts);
    }

    void ConstructLocations() {

        Location.DeepWell = new Location("Locations/DeepWell.json");
        Location.LongHall = new Location("Locations/LongHall.json");
        Location.Garden = new Location("Locations/Garden.json");
        Location.Courtroom = new Location("Locations/Courtroom.json");
        Location.MarchHaresHouse = new Location("Locations/MarchHaresHouse.json");
        Location.DuchessHouse = new Location("Locations/DuchessHouse.json");
        Location.CroquetPlayground = new Location("Locations/CroquetPlayground.json");
        Location.RabbitsHouse = new Location("Locations/RabbitsHouse.json");
        Location.Shores = new Location("Locations/Shores.json");
        Location.SafeRoom = new Location("Locations/SafeRoom.json");
    }

    void ReadMetaTexts() {
        String FileContents = "";

        try {
            FileReader fr = new FileReader("Texts.json");
            int i;
            while((i=fr.read())!=-1)
                FileContents += (char)i;
            fr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }


        JSONObject LocObj = new JSONObject(FileContents);


        for (Object i: (JSONArray)LocObj.get("Introduction")) {
            this.IntroductionTexts.add((String) i);
        }
    }
}
