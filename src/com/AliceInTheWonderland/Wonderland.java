package com.AliceInTheWonderland;

import java.util.HashMap;

public class Wonderland {

    HashMap<String, Location> Locations;

    public static void main(String[] args) {
        Wonderland GameSession = new Wonderland();
    }

    Wonderland() {
        PostWelcomeMessage();
        ConstructLocations();
    }

    void PostWelcomeMessage() {
        System.out.println("\n");
        System.out.println("Welcome to Wonderland!");
        System.out.println("\n");
    }

    void ConstructLocations() {

        Locations = new HashMap<String, Location>();

        Locations.put("LongHall", new Location("Locations/LongHall.json"));
        Locations.put("Garden", new Location("Locations/Garden.json"));
        Locations.put("Courtroom", new Location("Locations/Courtroom.json"));
        Locations.put("MarchHaresHouse", new Location("Locations/MarchHaresHouse.json"));
        Locations.put("DuchessHouse", new Location("Locations/DuchessHouse.json"));
        Locations.put("CroquetPlayground", new Location("Locations/CroquetPlayground.json"));
        Locations.put("RabbitsHouse", new Location("Locations/RabbitsHouse.json"));
        Locations.put("Shores", new Location("Locations/Shores.json"));
        Locations.put("SafeRoom", new Location("Locations/SafeRoom.json"));
    }
}
