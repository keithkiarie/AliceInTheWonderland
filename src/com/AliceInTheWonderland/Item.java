package com.AliceInTheWonderland;

import java.util.ArrayList;

public class Item {
    public static Item Gem, Jar, Map, DrinkMeBottle, EatMeCake, Mushroom, Glove, Fan, GoldenKey;

    public String Name;
    public boolean Edible;
    public ArrayList<Character> CharactersWhoAccept;

    Item(String Name, ArrayList<Character> CharactersWhoAccept, boolean Edible) {
        this.Name = Name;
        this.CharactersWhoAccept = CharactersWhoAccept;
        this.Edible = Edible;
    }

    static void MakeItems() {
        Item.Gem = new Item("Gem", new ArrayList<Character>(), false);
        Item.Jar = new Item("Jar", new ArrayList<Character>(), false);
        Item.Map = new Item("Map", new ArrayList<Character>(), false);
        Item.DrinkMeBottle = new Item("DrinkMeBottle", new ArrayList<Character>(), true);
        Item.EatMeCake = new Item("EatMeCake", new ArrayList<Character>(), true);
        Item.Mushroom = new Item("Mushroom", new ArrayList<Character>(), true);
        Item.Glove = new Item("White glove", new ArrayList<Character>(), false);
        Item.Fan = new Item("Hand fan", new ArrayList<Character>(), false);


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

    static void ConsumeItem(Item item) {
        if (!item.Edible) return;
        if (!Inventory.HasItem(item)) return;

        if (item.Name.compareToIgnoreCase(Item.DrinkMeBottle.Name) == 0) {
            Wonderland.AliceSize = Size.Short;
            Control.DropItem(item.Name);

        } else if (item.Name.compareToIgnoreCase(Item.EatMeCake.Name) == 0) {
            Wonderland.AliceSize = Size.Tall;
            Control.DropItem(item.Name);

        } else if (item.Name.compareToIgnoreCase(Item.Mushroom.Name) == 0) {

            if (Wonderland.AliceSize == Size.Normal) Wonderland.AliceSize = Size.Short;
            else if (Wonderland.AliceSize == Size.Tall) Wonderland.AliceSize = Size.Short;
            else if (Wonderland.AliceSize == Size.Short) Wonderland.AliceSize = Size.Tall;
        }
    }
}
