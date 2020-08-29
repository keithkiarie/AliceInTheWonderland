package com.AliceInTheWonderland;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public HashMap<CardinalPoint, Location> Entries = new HashMap<CardinalPoint, Location>();
    public HashMap<CardinalPoint, Location> Exits = new HashMap<CardinalPoint, Location>();

    public

    Location(String FileUrl) {
        try {
            this.GetFileData(FileUrl);
        } catch (IOException e) {
            System.out.println("Location file missing or contains invalid data. " + FileUrl);
            e.printStackTrace();
        }
    }

    void AddItem(Item item) {
        this.Items.add(item);
    }

    static void ConstructLocations() {

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


        // Entries and Exits
        Location.DeepWell.Exits.put(CardinalPoint.Up,Location.LongHall);

        Location.LongHall.Entries.put(CardinalPoint.South, Location.SafeRoom);
        Location.LongHall.Exits.put(CardinalPoint.West, Location.Shores);
        Location.LongHall.Exits.put(CardinalPoint.East, Location.Garden);

        Location.Garden.Entries.put(CardinalPoint.West, Location.LongHall);
        Location.Garden.Entries.put(CardinalPoint.South, Location.Courtroom);
        Location.Garden.Exits.put(CardinalPoint.Southwest, Location.SafeRoom);

        Location.Courtroom.Entries.put(CardinalPoint.West, Location.SafeRoom);
        Location.Courtroom.Exits.put(CardinalPoint.North, Location.Garden);

        Location.MarchHaresHouse.Entries.put(CardinalPoint.West, Location.DuchessHouse);
        Location.MarchHaresHouse.Exits.put(CardinalPoint.Northwest, Location.SafeRoom);

        Location.DuchessHouse.Entries.put(CardinalPoint.North, Location.SafeRoom);
        Location.DuchessHouse.Exits.put(CardinalPoint.East, Location.MarchHaresHouse);
        Location.DuchessHouse.Exits.put(CardinalPoint.West, Location.CroquetPlayground);

        Location.CroquetPlayground.Entries.put(CardinalPoint.East, Location.DuchessHouse);
        Location.CroquetPlayground.Exits.put(CardinalPoint.Northeast, Location.SafeRoom);

        Location.RabbitsHouse.Entries.put(CardinalPoint.North, Location.Shores);
        Location.RabbitsHouse.Exits.put(CardinalPoint.East, Location.SafeRoom);

        Location.Shores.Entries.put(CardinalPoint.East, Location.LongHall);
        Location.Shores.Exits.put(CardinalPoint.South, Location.RabbitsHouse);
    }


    public static CardinalPoint TextToCardinalPoint(String cardinalPoint) {

        switch (cardinalPoint) {
            case "North":
                return CardinalPoint.North;
            case "Northeast":
                return CardinalPoint.Northeast;
            case "East":
                return CardinalPoint.East;
            case "Southeast":
                return CardinalPoint.Southeast;
            case "South":
                return CardinalPoint.South;
            case "Southwest":
                return CardinalPoint.Southwest;
            case "West":
                return CardinalPoint.West;
            case "Northwest":
                return CardinalPoint.Northwest;
            case "Central":
                return CardinalPoint.Central;
            case "Up":
                return CardinalPoint.Up;
        }

        throw new IllegalArgumentException();
    }

    private void GetFileData(String FileUrl) throws IOException {
        String FileContents = "";

        try {
            FileReader fr = new FileReader(FileUrl);
            int i;
            while ((i = fr.read()) != -1)
                FileContents += (char) i;
            fr.close();
        } catch (IOException e) {
            throw e;
        }


        JSONObject LocObj = new JSONObject(FileContents);


        this.Name = (String) LocObj.get("Name");
        this.CardinalLocation = TextToCardinalPoint((String) LocObj.get("CardinalLocation"));

        for (Object i : (JSONArray) LocObj.get("Texts")) {
            this.Texts.add((String) i);
        }
    }
}
