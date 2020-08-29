package com.AliceInTheWonderland;

public class Item {
    public String Name;
    public static Item Gem, Map, DrinkMeBottle, EatMeCake, Mushroom;

    Item(String Name) {
        this.Name = Name;
    }

    static void MakeItems() {
        Item.Gem = new Item("Gem");
        Item.Map = new Item("Map");
        Item.DrinkMeBottle = new Item("DrinkMeBottle");
        Item.EatMeCake = new Item("EatMeCake");
        Item.Mushroom = new Item("Mushroom");
    }
}
