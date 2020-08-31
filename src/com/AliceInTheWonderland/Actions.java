package com.AliceInTheWonderland;

import java.awt.desktop.SystemEventListener;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class Actions {
    public static JSONObject ActionsJSON;


    public static void ReadActionsFile() throws IOException {
        String FileContents = "";

        try {
            FileReader fr = new FileReader("Actions.json");
            int i;
            while ((i = fr.read()) != -1)
                FileContents += (char) i;
            fr.close();
        } catch (IOException e) {
            throw e;
        }

        ActionsJSON = new JSONObject(FileContents);
    }

    Actions() {
        try {
            ReadActionsFile();
        } catch (IOException e) {
            System.out.println("Error accessing Actions file");
        }
    }


    public static void DeepWell() {
        Location location = Location.DeepWell;

        // introduction to deep well
        System.out.println(location.Texts.get(0));

        location.Visited = true;

        // she sees a jar
        System.out.println(location.Texts.get(1));
        Control.GetUserInput(location, PossibleActions.CollectItem);

        if (Inventory.HasItem(Item.Jar)) {
            System.out.println(location.Texts.get(2));
            Control.GetUserInput(location, PossibleActions.CollectItem);
        }

        // she sees map hanging on the wall
        if (location.Items.get(0) == Item.Gem) Wonderland.CurrentLocation.Items.remove(0);

        System.out.println(location.Texts.get(3));
        Control.GetUserInput(location, PossibleActions.CollectItem);


        System.out.println(location.Texts.get(4));

        // she falls in the long hall
        Control.GoToLocation(Location.LongHall);
    }


    public static void LongHall() {
        Location location = Location.LongHall;

        // on entering
        if (!location.Visited) System.out.println(location.Texts.get(0));
        else System.out.println(location.Texts.get(1));

        location.Visited = true;

        // collect golden key
        System.out.println(location.Texts.get(2));
        Control.GetUserInput(location, PossibleActions.CollectItem);

        // drink me bottle and eat me cake
        System.out.println(location.Texts.get(3));
        Control.GetUserInput(location, PossibleActions.CollectItem);

        if (!Wonderland.ConsumptionAtLongHall) {
            System.out.println(location.Texts.get(4));
            Control.GetUserInput(location, PossibleActions.CollectItem);
        }

        if (!Wonderland.ConsumptionAtLongHall) {
            if (Location.LongHall.Items.size() == 0 && !Inventory.HasItem(Item.DrinkMeBottle) && !Inventory.HasItem(Item.EatMeCake)) {
                Location.LongHall.AddItem(Item.DrinkMeBottle);
                Location.LongHall.AddItem(Item.EatMeCake);

                System.out.println(location.Texts.get(9));
                Control.GetUserInput(location, PossibleActions.CollectItem);

                System.out.println(location.Texts.get(10));
                Control.GetUserInput(location, PossibleActions.CollectItem);

            } else if (Inventory.HasItem(Item.DrinkMeBottle) || Inventory.HasItem(Item.EatMeCake)) {
                System.out.println(location.Texts.get(8));
                Control.GetUserInput(location, PossibleActions.CollectItem);
            }
        }
    }

    public static void DrinkInLongHall() {
        Wonderland.ConsumptionAtLongHall = true;

        Location location = Location.LongHall;

        if (Inventory.HasItem(Item.GoldenKey)) {
            // going through the small door
            Location.LongHall.Exits.put(CardinalPoint.East, Location.Garden);

            System.out.println(location.Texts.get(5));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else if (!Inventory.HasItem(Item.GoldenKey)) {
            // she has to try the cake
            System.out.println(location.Texts.get(6));
            Control.GetUserInput(location, PossibleActions.CollectItem);
        }
    }

    public static void EatCakeInLongHall() {
        Wonderland.ConsumptionAtLongHall = true;

        // she becomes so tall, she cries, then she shrinks
        Location location = Location.LongHall;
        System.out.println(location.Texts.get(7));

        Wonderland.CriedAtLongHall = true;

        Wonderland.AliceSize = Size.Short;

        Location.LongHall.Exits.put(CardinalPoint.West, Location.Shores);
        Control.GoToLocation(Location.Shores);
    }

    public static void Garden() {
        Location location = Location.Garden;

        if (!location.Visited && !Location.Shores.Visited) {
            Wonderland.GamePlot = Plot.Duchess;
            System.out.println(location.Texts.get(0));
        } else {
            System.out.println(location.Texts.get(1));
        }

        location.Visited = true;

        Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
    }

    public static void Shores() {
        Location location = Location.Shores;

        if (!location.Visited && !Location.Garden.Visited) {
            location.Visited = true;

            // first time visiting and the plot is around the white rabbit
            Wonderland.GamePlot = Plot.Rabbit;
            System.out.println(location.Texts.get(0));

            // prize for the contest
            if (Inventory.Count() == 0) Wonderland.GameOver(location.Texts.get(3));
            Control.GetUserInput(location, PossibleActions.GiveItem);

            if (Wonderland.PaidPrizeForTheRace) {
                System.out.println(location.Texts.get(4));
                Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
            }

        } else if (location.Visited && Wonderland.CriedAtLongHall) {
            // visiting here again and she hand cried before
            System.out.println(location.Texts.get(1));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else {
            location.Visited = true;

            System.out.println(location.Texts.get(2));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
        }


    }

    public static void RabbitsHouse() {
        Location location = Location.RabbitsHouse;

        if (!location.Visited) {
            System.out.println(location.Texts.get(0));
            Control.GetUserInput(location, PossibleActions.CollectItem); // mushroom
        }

        if (!location.Visited && Wonderland.GamePlot == Plot.Rabbit) {
            Location.LongHall.AddItem(Item.GloveAndFan);

            System.out.println(location.Texts.get(1)); //he gives you instructions
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else if (location.Visited && Wonderland.GamePlot == Plot.Rabbit && !Wonderland.PaidTheRabbit) {
            System.out.println(location.Texts.get(2));

            if (!Inventory.HasItem(Item.GloveAndFan)) {
                System.out.println(location.Texts.get(5));
                Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

            } else if (Inventory.HasItem(Item.GloveAndFan)) {
                System.out.println(location.Texts.get(2));
                Control.GetUserInput(location, PossibleActions.GiveItem);
            }

            if (Wonderland.PaidTheRabbit && !Wonderland.TimeIsTakenBack) {
                System.out.println(location.Texts.get(3));
                Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
            }


        } else if (location.Visited && Wonderland.GamePlot == Plot.Rabbit && Wonderland.PaidTheRabbit && Wonderland.TimeIsTakenBack) {
            Wonderland.GameOver(location.Texts.get(4));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else if (location.Visited && Wonderland.GamePlot == Plot.Rabbit && !Wonderland.TimeIsTakenBack && Wonderland.PaidTheRabbit) {
            System.out.println(location.Texts.get(3));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);

        } else  {
            System.out.println(location.Texts.get(5));
        }

        location.Visited = true;
    }

    public static void MarchHaresHouse() {
        Location location = Location.MarchHaresHouse;

        if ((Wonderland.GamePlot == Plot.Rabbit && Location.RabbitsHouse.Visited)
                || (Wonderland.GamePlot == Plot.Duchess && Location.DuchessHouse.Visited) && !Wonderland.TimeIsTakenBack) {
            System.out.println(location.Texts.get(0));
            Wonderland.TimeIsTakenBack = true;
        } else {
            System.out.println(location.Texts.get(1));
        }

        location.Visited = true;

        Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
    }

    public static void Courtroom() {
        Location location = Location.Courtroom;

        if (!location.Visited && Wonderland.GamePlot == Plot.Duchess) {
            System.out.println(location.Texts.get(0));
            Control.GetUserInput(location, PossibleActions.YesOrNo);

            if (Wonderland.YesOrNo == YesOrNo.Yes) Wonderland.GameOver(location.Texts.get(3));
            else Wonderland.GameOver(location.Texts.get(2));
        } else {
            System.out.println(location.Texts.get(1));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
        }
    }

    public static void DuchessHouse() {
        Location location = Location.DuchessHouse;

        if (!location.Visited && Wonderland.GamePlot == Plot.Duchess) {
            System.out.println(location.Texts.get(0));
        } else if ((Wonderland.GamePlot == Plot.Duchess || !Wonderland.TimeIsTakenBack) && !Location.CroquetPlayground.Visited) {
            System.out.println(location.Texts.get(2));
        } else {
            System.out.println(location.Texts.get(1));
        }
        location.Visited = true;
        Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
    }

    public static void CroquetPlayground() {
        Location location = Location.CroquetPlayground;

        if (!location.Visited && Wonderland.GamePlot == Plot.Duchess) {
            System.out.println(location.Texts.get(0));
            Control.GetUserInput(location, PossibleActions.YesOrNo);

            if (Wonderland.YesOrNo == YesOrNo.Yes) {
                System.out.println(location.Texts.get(2));
                Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
            } else {
                Wonderland.GameOver(location.Texts.get(3));
            }
        } else {
            System.out.println(location.Texts.get(1));
            Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
        }

        location.Visited = true;
    }

    public static void SafeRoom() {
        Location location = Location.SafeRoom;

        if (!location.Visited) {
            System.out.println(location.Texts.get(0));
            location.Visited = true;
        } else {
            System.out.println(location.Texts.get(1));
            if (location.Items.size() == 0) {
                System.out.println("\tThere is no stored item.");
            } else {
                System.out.println("You have stored a " + location.Items.get(0).Name);
                System.out.println("If you wish to collect it type 'collect " + location.Items.get(0).Name + "'. If you don't press 'Enter'.");
                Control.GetUserInput(location, PossibleActions.CollectItem);
            }
        }
        System.out.println("You can drop an item or just continue your journey:");
        Control.GetUserInput(location, PossibleActions.AllowChangeOfLocation);
    }
}
