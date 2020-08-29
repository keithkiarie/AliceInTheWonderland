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

    public ArrayList<Location> Entries = new ArrayList<Location>();
    public ArrayList<Location> Exits = new ArrayList<Location>();

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

        Location.DeepWell.Exits.add(Location.LongHall);

        Location.LongHall.Entries.add(Location.SafeRoom);
        Location.LongHall.Exits.add(Location.Shores);
        Location.LongHall.Exits.add(Location.Garden);

        Location.Garden.Entries.add(Location.LongHall);
        Location.Garden.Entries.add(Location.Courtroom);
        Location.Garden.Exits.add(Location.SafeRoom);

        Location.Courtroom.Entries.add(Location.SafeRoom);
        Location.Courtroom.Exits.add(Location.Garden);

        Location.MarchHaresHouse.Entries.add(Location.DuchessHouse);
        Location.MarchHaresHouse.Exits.add(Location.SafeRoom);

        Location.DuchessHouse.Entries.add(Location.SafeRoom);
        Location.DuchessHouse.Exits.add(Location.MarchHaresHouse);
        Location.DuchessHouse.Exits.add(Location.CroquetPlayground);

        Location.CroquetPlayground.Entries.add(Location.DuchessHouse);
        Location.CroquetPlayground.Exits.add(Location.SafeRoom);

        Location.RabbitsHouse.Entries.add(Location.Shores);
        Location.RabbitsHouse.Exits.add(Location.SafeRoom);

        Location.Shores.Entries.add(Location.LongHall);
        Location.Shores.Exits.add(Location.RabbitsHouse);
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
