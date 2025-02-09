package com.AliceInTheWonderland;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;

enum CardinalPoint {
    North, Northeast, East, Southeast, South, Southwest, West, Northwest, Central
}

public class Location {
    public CardinalPoint CardinalLocation;
    public String Name;
    public int id;
    public ArrayList<String> Texts;
    public ArrayList<Item> Items;
    public boolean Visited = false;

    public static Location DeepWell, LongHall, Garden, Courtroom, MarchHaresHouse, DuchessHouse,
            CroquetPlayground, RabbitsHouse, Shores, SafeRoom;

    public HashMap<CardinalPoint, Location> Entries;
    public HashMap<CardinalPoint, Location> Exits;

    public Location(String FileUrl, int id) {
        this.id = id;

        this.Texts = new ArrayList<String>();
        this.Items = new ArrayList<Item>();
        this.Entries = new HashMap<CardinalPoint, Location>();
        this.Exits = new HashMap<CardinalPoint, Location>();

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

    void RemoveItem(Item item) {
        int index = 0;
        for (Item i: this.Items) {
            if (i.Name.compareToIgnoreCase(item.Name) == 0) {
                this.Items.remove(index);
                return;
            }
            index++;
        }
    }

    static void ConstructLocations() {

        Location.DeepWell = new Location("Locations/DeepWell.json", 0);
        Location.LongHall = new Location("Locations/LongHall.json", 1);
        Location.Garden = new Location("Locations/Garden.json", 2);
        Location.Courtroom = new Location("Locations/Courtroom.json", 3);
        Location.MarchHaresHouse = new Location("Locations/MarchHaresHouse.json", 4);
        Location.DuchessHouse = new Location("Locations/DuchessHouse.json", 5);
        Location.CroquetPlayground = new Location("Locations/CroquetPlayground.json", 6);
        Location.RabbitsHouse = new Location("Locations/RabbitsHouse.json", 7);
        Location.Shores = new Location("Locations/Shores.json", 8);
        Location.SafeRoom = new Location("Locations/SafeRoom.json", 9);


        // Entries and Exits
        Location.DeepWell.Exits.put(CardinalPoint.North, Location.LongHall);

        Location.LongHall.Entries.put(CardinalPoint.South, Location.SafeRoom);


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

        Location.SafeRoom.Exits.put(CardinalPoint.North, Location.LongHall);
        Location.SafeRoom.Exits.put(CardinalPoint.South, Location.DuchessHouse);
        Location.SafeRoom.Exits.put(CardinalPoint.East, Location.Courtroom);
    }


    public static CardinalPoint TextToCardinalPoint(String cardinalPoint) {

        switch (cardinalPoint.toLowerCase()) {
            case "north":
            case "n":
                return CardinalPoint.North;
            case "northeast":
            case "ne":
                return CardinalPoint.Northeast;
            case "east":
            case "e":
                return CardinalPoint.East;
            case "southeast":
            case "se":
                return CardinalPoint.Southeast;
            case "south":
            case "s":
                return CardinalPoint.South;
            case "southwest":
            case "sw":
                return CardinalPoint.Southwest;
            case "west":
            case "w":
                return CardinalPoint.West;
            case "northwest":
            case "nw":
                return CardinalPoint.Northwest;
            case "central":
            case "c":
                return CardinalPoint.Central;
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
