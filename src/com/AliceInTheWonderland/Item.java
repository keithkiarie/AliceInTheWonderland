package com.AliceInTheWonderland;

import java.util.ArrayList;

public class Item {
    public String Name;
    public static Item Gem, Jar, Map, DrinkMeBottle, EatMeCake, Mushroom, Glove, Fan, GoldenKey;

    public ArrayList<Character> CharactersWhoAccept;

    Item(String Name, ArrayList<Character> CharactersWhoAccept) {
        this.Name = Name;
        this.CharactersWhoAccept = CharactersWhoAccept;
    }

    static void MakeItems() {
        Item.Gem = new Item("Gem", new ArrayList<Character>());
        Item.Jar = new Item("Jar", new ArrayList<Character>());
        Item.Map = new Item("Map", new ArrayList<Character>());
        Item.DrinkMeBottle = new Item("DrinkMeBottle", new ArrayList<Character>());
        Item.EatMeCake = new Item("EatMeCake", new ArrayList<Character>());
        Item.Mushroom = new Item("Mushroom", new ArrayList<Character>());
        Item.Glove = new Item("White glove", new ArrayList<Character>());
        Item.Fan = new Item("Hand fan", new ArrayList<Character>());


        // characters who can accept an item
        Item.Gem.CharactersWhoAccept.add(Character.MarchHare);
        Item.Glove.CharactersWhoAccept.add(Character.Rabbit);
        Item.Fan.CharactersWhoAccept.add(Character.Rabbit);
    }

    static void PlaceItemsInLocations() {
        Location.DeepWell.AddItem(Item.Jar);
        Location.DeepWell.AddItem(Item.Gem);
        Location.DeepWell.AddItem(Item.Map);

        Location.LongHall.AddItem(Item.GoldenKey);
        Location.LongHall.AddItem(Item.DrinkMeBottle);
        Location.LongHall.AddItem(Item.EatMeCake);
        Location.LongHall.AddItem(Item.Fan);
        Location.LongHall.AddItem(Item.Glove);
    }
}
