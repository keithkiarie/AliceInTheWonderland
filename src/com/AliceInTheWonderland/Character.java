package com.AliceInTheWonderland;

// only characters that directly influence the plot
public class Character {

    public String Name;
    public Location location;
    public boolean RequiresItem;

    public static Character Rabbit, MarchHare, Queen, King, Gardeners, Duchess;

    public static void MakeCharacters() {
        Rabbit = new Character("Rabbit", Location.RabbitsHouse, true);
        MarchHare = new Character("March Hare", Location.MarchHaresHouse, true);
        Queen = new Character("Queen", Location.CroquetPlayground, false);
        King = new Character("King", Location.Courtroom, false);
        Gardeners = new Character("King", Location.Garden, false);
        Duchess = new Character("Duchess", Location.DuchessHouse, false);
    }

    Character(String Name, Location location, boolean RequiresItem) {
        this.Name = Name;
        this.location = location;
        this.RequiresItem = RequiresItem;
    }
}
