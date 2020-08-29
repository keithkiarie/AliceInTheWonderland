package com.AliceInTheWonderland;

import java.util.HashMap;

// Plot is influenced by who is met first between the Gardeners and the Rabbit, determining who will help you
enum Plot {
    Duchess, Rabbit
}

public class Wonderland {

    public static Plot GamePlot;
    public static boolean CriedAtLongHall = false;
    public static boolean PaidRabbit = false;
    public static boolean TimeIsTakenBack = false;


    public static Location CurrentLocation;

    public static int Plot = 1;

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
}
