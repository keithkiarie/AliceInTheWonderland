package com.AliceInTheWonderland;

public class Wonderland {

    public static void main(String[] args) {
	    PostWelcomeMessage();
	    Location LongHall = new Location("Locations/LongHall.json");
    }

    static void PostWelcomeMessage() {
        System.out.println("\n");
        System.out.println("Welcome to Wonderland!");
        System.out.println("\n");
    }
}
