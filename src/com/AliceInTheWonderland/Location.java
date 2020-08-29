package com.AliceInTheWonderland;


import java.io.FileReader;
import java.io.IOException;
import org.json.*;

enum CardinalPoint {
    North, Northeast, East, Southeast, South, Southwest, West, Northwest, Central
}

public class Location {
    public CardinalPoint CardinalLocation;
    public String Name;
    public String FirstVisitText;
    public String SubsequentVisitText;

    Location(String FileUrl){
        try {
            this.GetFileData(FileUrl);
        } catch (IOException e) {
            System.out.println("Location file missing or contains invalid data. " + FileUrl);
            e.printStackTrace();
        }
    }

    private void GetFileData(String FileUrl) throws IOException {
        String FileContents = "";


        try {
            FileReader fr = new FileReader(FileUrl);
            int i;
            while((i=fr.read())!=-1)
                FileContents += (char)i;
            fr.close();
        } catch(IOException e) {
            throw e;
        }




        JSONObject LocObj = new JSONObject(FileContents);

        // getting Location name
        String Name = (String) LocObj.get("Name");
        String FirstVisitText = (String) LocObj.get("FirstVisitText");
        String SubsequentVisitText = (String) LocObj.get("SubsequentVisitText");


        this.Name = Name;
        this.CardinalLocation = CardinalLocation;
        this.FirstVisitText = FirstVisitText;
        this.SubsequentVisitText = SubsequentVisitText;
        this.CardinalLocation = TextToCardinalPoint((String) LocObj.get("CardinalLocation"));
    }

    public static CardinalPoint TextToCardinalPoint(String cardinalPoint) {

        switch (cardinalPoint) {
            case "North": return CardinalPoint.North;
            case "Northeast": return CardinalPoint.Northeast;
            case "East": return CardinalPoint.East;
            case "Southeast": return CardinalPoint.Southeast;
            case "South": return CardinalPoint.South;
            case "Southwest": return CardinalPoint.Southwest;
            case "West": return CardinalPoint.West;
            case "Northwest": return CardinalPoint.Northwest;
            case "Central": return CardinalPoint.Central;
        }

        throw new IllegalArgumentException();
    }
}
