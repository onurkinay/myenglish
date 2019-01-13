package tr.com.onurkinay.myenglish;

import org.json.JSONObject;

public class wordOrder {
    public int ID;
    public String English;
    public String Turkish;
    public String Sample;
    public String Mean;
    public String Type;

    public wordOrder(int id, JSONObject word) {
        try {
            ID = id;
            English = word.getString("English");
            Turkish = word.getString("Turkish");
            Sample = word.getString("Sample");
            Mean = word.getString("Mean");
            Type = word.getString("Type");

        } catch (Exception e) {

        }
    }
    public wordOrder(int id, String eng, String tur, String sample, String mean, String type){
        try {

            ID = id;
            English = eng;
            Turkish = tur;
            Sample = sample;
            Mean = mean;
            Type = type;

        } catch (Exception e) {

        }
    }
}
