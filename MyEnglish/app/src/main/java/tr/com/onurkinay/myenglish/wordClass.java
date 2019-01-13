package tr.com.onurkinay.myenglish;


import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;
import static android.content.Context.*;

public class wordClass {
    private JSONArray Words = new JSONArray();
    private JSONObject JSONAll = new JSONObject();
    public String dbName = "database.json";

    public wordClass(Context ctx) {
        String s = "";
        try {
            File file = ctx.getFileStreamPath(dbName);
            if (file == null || !file.exists()) {
                FileOutputStream fileout = ctx.openFileOutput(dbName, MODE_PRIVATE);
                OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
                outputWriter.write("{\"Word\":[{\"English\":\"Hello\",\"Turkish\":\"Merhaba\",\"Sample\":\"Hello guys!\",\"Mean\":\"a greeting word\",\"Type\":\"Noun\"},{\"English\":\"World\",\"Turkish\":\"Dünya\",\"Sample\":\"We live in a different world\",\"Mean\":\"the earth, together with all of its countries, peoples, and natural features\",\"Type\":\"Noun\"}]}\n");
                outputWriter.close();
            }
            FileInputStream fileIn = ctx.openFileInput(dbName);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];

            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                s += readstring;
            }
            InputRead.close();
            JSONAll = new JSONObject(s);

            Words = JSONAll.getJSONArray("Word");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean SaveJSON(Context ctx) {
        try {
            FileOutputStream fileout = ctx.openFileOutput(dbName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);

           /* JSONAll.remove("Word");
            JSONAll.put("Word", Words.toString());*/

            outputWriter.write(JSONAll.toString());
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;

    }
    public boolean RestoreJSON(String restore, Context ctx){
        try {
            FileOutputStream fileout = ctx.openFileOutput(dbName, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);

            outputWriter.write(restore);
            outputWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    public String BackupJSON(Context ctx){
        try {
            String ss = "";
            FileInputStream fileIn = ctx.openFileInput(dbName);
            InputStreamReader InputRead = new InputStreamReader(fileIn);

            char[] inputBuffer = new char[100];

            int charRead;

            while ((charRead = InputRead.read(inputBuffer)) > 0) {
                // char to string conversion
                String readstring = String.copyValueOf(inputBuffer, 0, charRead);
                ss += readstring;
            }
            InputRead.close();
            return ss;
        }catch (Exception e){
            return "";
        }

    }

    public boolean addWord(wordOrder newWord, Context ctx) {
        try {

            JSONObject word = new JSONObject();

            word.put("English", newWord.English);
            word.put("Turkish", newWord.Turkish);
            word.put("Sample", newWord.Sample);
            word.put("Mean", newWord.Mean);
            word.put("Type", newWord.Type);
            Words.put(word);

            SaveJSON(ctx);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteWord(int deleteWord, Context ctx) {
        try{
             Words.remove(deleteWord);
             SaveJSON(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<wordOrder> getWords() {
        ArrayList<wordOrder> returnWord = new ArrayList<>();

        try {
            for (int i = 0; i < Words.length(); i++) {

                wordOrder word = new wordOrder(i,Words.getJSONObject(i));
                returnWord.add(word);

            }
            return returnWord;
        } catch (Exception e) {
            e.printStackTrace();
            return returnWord;
        }

    }

    public wordOrder getWord(int id) {
        try {
            if(id == -1) {
                wordOrder word = new wordOrder(Words.length()-1, Words.getJSONObject(Words.length()-1) );
                return word;
            }else{
                wordOrder word = new wordOrder(id, Words.getJSONObject(id));
                return word;
            }
        } catch (Exception e) {
            return new wordOrder(-1,new JSONObject());
        }
    }

    public wordOrder randomGetWord(){
        try {
        Random rand = new Random();
        int rID = rand.nextInt( Words.length() );
            wordOrder word = new wordOrder(rID, Words.getJSONObject(rID));
        return word;
        } catch (Exception e) {
        return new wordOrder(-1, new JSONObject());
    }
    }
}
