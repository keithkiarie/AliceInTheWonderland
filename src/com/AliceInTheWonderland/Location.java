package com.AliceInTheWonderland;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.*;

enum CardinalPoint {
    North, Northeast, East, Southeast, South, Southwest, West, Northwest, Central, Up
}

public class Location {
    public CardinalPoint CardinalLocation;
    public String Name;
    public ArrayList<String> Texts = new ArrayList<String>();
    public ArrayList<Item> Items = new ArrayList<Item>();
    public boolean Visited = false;

    public static Location DeepWell, LongHall, Garden, Courtroom, MarchHaresHouse, DuchessHouse,
            CroquetPlayground, RabbitsHouse, Shores, SafeRoom;

    Location(String FileUrl){
        try {
            this.GetFileData(FileUrl);
        } catch (IOException e) {
            System.out.println("Location file missing or contains invalid data. " + FileUrl);
            e.printStackTrace();
        }
    }



    public static CardinalPoint TextToCardinalPoint(String cardinalPoint) {

        switch (cardinalPoint) {
            case "North": return CardinalPoint.North;
            case "Northeast": return CardinalPoint.Northeast;
            case "East": return CardinalPoint.East;
            case "Southeast": return CardinalPoint.Southeast;
            case "South": return CardinalPoint.South;
            case "Southwest": return CardinalPoint.Southwest;
            case "West": return CardinalPoint.West;
            case "Northwest": return CardinalPoint.Northwest;
            case "Central": return CardinalPoint.Central;
            case "Up": return CardinalPoint.Up;
        }

        throw new IllegalArgumentException();
    }

    private void GetFileData(String FileUrl) throws IOException {
        String FileContents = "";

        try {
            FileReader fr = new FileReader(FileUrl);
            int i;
            while((i=fr.read())!=-1)
                FileContents += (char)i;
            fr.close();
        } catch(IOException e) {
            throw e;
        }


        JSONObject LocObj = new JSONObject(FileContents);


        this.Name = (String) LocObj.get("Name");
        this.CardinalLocation = TextToCardinalPoint((String) LocObj.get("CardinalLocation"));

        for (Object i: (JSONArray)LocObj.get("Texts")) {
            this.Texts.add((String) i);
        }
    }
}
