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
    public ArrayList<String>InstructionsTexts = new ArrayList<String>();

    public static Location CurrentLocation;

    public static int Plot = 1;

    public static void main(String[] args) {
        Wonderland GameSession = new Wonderland();
    }

    Wonderland() {
        Location.ConstructLocations();
        Item.MakeItems();


        ReadMetaTexts();

        PostWelcomeMessage();
        PostInstructionsMessage();

        Actions.DeepWell();
    }

    void PostWelcomeMessage() {
        Control.PrintDelayedTexts(this.IntroductionTexts);
    }

    void PostInstructionsMessage() {
        Control.PrintDelayedTexts(this.InstructionsTexts);
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
        for (Object i: (JSONArray)LocObj.get("Instructions")) {
            this.InstructionsTexts.add((String) i);
        }
    }
}
