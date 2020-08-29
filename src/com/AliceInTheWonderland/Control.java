package com.AliceInTheWonderland;

import java.util.ArrayList;

public class Control {

    // called from a location (in Actions). Takes in a list of items available in that room
    public static void GetUserInput(Location location) {

    }

    public static void DisplayCollectibleItems(Location location) {

    }

    public static void DisplayMap() {
        System.out.println("Map");
    }

    public static void GoToLocation(CardinalPoint location) {
        switch (location) {
            case North:
                Actions.LongHall();
                break;
            case Northeast:
                Actions.Garden();
                break;
            case East:
                Actions.Courtroom();
                break;
            case Southeast:
                Actions.MarchHaresHouse();
                break;
            case South:
                Actions.DuchessHouse();
                break;
            case Southwest:
                Actions.CroquetPlayground();
                break;
            case West:
                Actions.RabbitsHouse();
                break;
            case Northwest:
                Actions.Shores();
                break;
            case Central:
                Actions.SafeRoom();
                break;
        }
    }
}
