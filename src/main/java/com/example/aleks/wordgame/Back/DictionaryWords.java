package com.example.aleks.wordgame.Back;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class DictionaryWords {
    public static ArrayList<String> usedWords = new ArrayList<>();
    private static Context context;
    public static void addUsedWord(String word) {
        usedWords.add(word);
    }


    // Метод проверяет есть ли данное слово среди использованных слов
    public static Boolean isWordInUsed(String word) {
        for (String s : usedWords) {
            if (word.equals(s))
                return true;
        }
        return false;
    }

    // Метод проверяет есть ли данное слово в словаре
    public static Boolean isWordInDict(String word, Context context) {
        AssetManager assetManager = context.getAssets();
        try {
            String baseDir = Environment.getExternalStorageDirectory()+ File.separator +  "Words";

            //FileInputStream fstream = new FileInputStream(baseDir + File.separator + Integer.toString(word.length()) + ".txt");
            InputStreamReader istream = new InputStreamReader(assetManager.open("Words"+ File.separator + Integer.toString(word.length()) + ".txt"));
            BufferedReader br = new BufferedReader(istream);
            String strLine;
            while ((strLine = br.readLine()) != null){
                if (word.equals(strLine)) {
                    br.close();
                    istream.close();
                    return true;
                }
            }
            br.close();
            istream.close();
            return false;
        } catch (IOException ex) {
            Log.i("Ошибка в файле", ex.toString());
            return false;
        }
    }

    // Метод ищет слово в словаре по параметрам
    public static String findWord(String pattern, int countLetter, int file, Context context) {
        AssetManager assetManager = context.getAssets();
        try {
           // String baseDir = Environment.getExternalStorageDirectory()+ File.separator +  "Words";
           // FileInputStream fStream = new FileInputStream(baseDir + File.separator + Integer.toString(file) + ".txt");
            //InputStreamReader istream = new InputStreamReader(assetManager.open("Words"+ File.separator + Integer.toString(file) + ".txt"));
            //final InputStream stream = context.getResources().getAssets().open("");

            InputStream istream = context.getResources().getAssets().open("Words"+ File.separator + Integer.toString(file) + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(istream));
            String strLine;

            while ((strLine = br.readLine()) != null){
                if (fromEnd(pattern, countLetter).equals(fromBegin(strLine, countLetter))) {
                    br.close();
                    istream.close();
                    return strLine;
                }
            }
            br.close();
            istream.close();
            return null;

        } catch (IOException ex) {
            System.out.println("Ошибка в файле");
            return null;
        }
    }

    // Метод генерирует случайное слово длиной 5-8
    public static String startWord(Context context) {

       // String baseDir = Environment.getExternalStorageDirectory()+ File.separator +  "Words";
        AssetManager assetManager = context.getAssets();

       // String baseDir = "file:///android_asset/Words/";
        Random rnd = new Random();
        int fileNumber = rnd.nextInt(4) + 5;
        try {
            InputStreamReader istream = new InputStreamReader(assetManager.open("Words"+ File.separator + Integer.toString(fileNumber) + ".txt"));
            //String path = Integer.toString(fileNumber) + ".txt";
            //File f = new File(baseDir +  File.separator + path);
            //FileInputStream fStream = new FileInputStream(f);
            BufferedReader br = new BufferedReader(istream);
            int count = 0;

            while ((br.readLine()) != null){
                count++;
            }
           // istream.mark(0);
            //fStream.getChannel().position(0);
            br = new BufferedReader(istream);

            int wordNumber = rnd.nextInt(count) + 1;
            for (int i = 0; i < wordNumber - 1; i++) {
                br.readLine();
            }
            String word = br.readLine();
            br.close();
            istream.close();
            return word;

        } catch (IOException ex) {
            Log.i("Ошибка в файле", ex.toString());
            return null;
        }
    }

    // Метод взятия count букв из строки str с начала
    private static String fromBegin(String str, int count) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < count; i++) {
            s.append(str.toCharArray()[i]);
        }
        return s.toString();
    }

    // Метод взятия count букв из строки str с конца
    private static String fromEnd(String str, int count) {
        StringBuilder s = new StringBuilder();
        for (int i = str.length() - count; i < str.length(); i++) {
            s.append(str.toCharArray()[i]);
        }
        return s.toString();
    }
}
