package com.example.aleks.wordgame.Back;

public class Player {
    private String name;
    private int score = 0;

    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }

    public Player(String name) {
        this.name = name;
    }

    // Метод для подсчета количества одинаковых букв
    public void count(String pattern, String word) {
        int count1 = 0;
        int count2 = 0;

        for (int i = 1; i <= Math.min(pattern.length(), word.length()); i++) {
            if (fromEnd(pattern, i).equals(fromBegin(word, i))) {
                count1 = i;
            }
        }

        pattern = pattern.replace("ь", "");
        pattern = pattern.replace("ъ", "");

        for (int i = 1; i <= Math.min(pattern.length(), word.length()); i++) {
            if (fromEnd(pattern, i).equals(fromBegin(word, i))) {
                count2 = i;
            }
        }

        if (count1 > count2) {
            score = score + count1;
        } else {
            score = score + count2;
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