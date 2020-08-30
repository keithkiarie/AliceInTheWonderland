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

enum Size {
    Short,
    Tall,
    Normal
}

enum YesOrNo {
    Yes, No
}

public class Wonderland {

    public static Plot GamePlot;
    public static boolean CriedAtLongHall = false;
    public  static boolean ConsumptionAtLongHall = false;

    public static boolean PaidTheRabbit = false;
    public static boolean PaidTheMarchHare = false;
    public static boolean PaidPrizeForTheRace = false;
    public static YesOrNo YesOrNo;

    public static boolean TimeIsTakenBack = false;
    public static Size AliceSize = Size.Normal;

    public static ArrayList<String>IntroductionTexts = new ArrayList<String>();
    public static ArrayList<String>InstructionsTexts = new ArrayList<String>();

    public static Location CurrentLocation;

    public static int Plot = 1;

    public static void main(String[] args) {
        Wonderland GameSession = new Wonderland();
    }

    Wonderland() {
        Item.MakeItems();
        Location.ConstructLocations();
        Item.PlaceItemsInLocations();

        CurrentLocation = Location.DeepWell;

        ReadMetaTexts();

        Control.PostWelcomeMessage();
        Control.PostInstructionsMessage();

        Actions.DeepWell();
    }



    static void GameOver(String EndingMessage) {
        System.out.println("\nGAME OVER:" + EndingMessage);
        System.exit(0);
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
            Wonderland.IntroductionTexts.add((String) i);
        }
        for (Object i: (JSONArray)LocObj.get("Instructions")) {
            Wonderland.InstructionsTexts.add((String) i);
        }
    }
}
