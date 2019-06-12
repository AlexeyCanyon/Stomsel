package com.example.aleks.wordgame.Back;

import android.content.Context;

import java.util.Random;

public class Bot {
    private int score = 0;
    private int level;
    private int interim = 0;

    public int getScore() {
        return score;
    }
    public Bot(int level) {
        this.level = level;
    }

    public void count() {
        score = score + interim;
    }

    // Метод обращается к словарю и находит в нем слово
    public String generateWord(String word, Context context) {
        int COUNT_FILE = 8;
        interim = howLetter(word.length());

        int[] filesNumber = new int[COUNT_FILE];
        for (int i = 0; i < COUNT_FILE; i++)
            filesNumber[i] = interim + i;
        mix(filesNumber);

        while (true) {
            for (int i = 0; i < COUNT_FILE; i++) {
                String newWord = DictionaryWords.findWord(word, interim, filesNumber[i], context);
                if (newWord != null)
                    return newWord;
            }
            if (word.contains("ь") || word.contains("ъ")) {
                word = word.replace("ь", "");
                word = word.replace("ъ", "");
                if (interim > 1)
                    interim--;
                continue;
            }
            interim--;
        }
    }

    // Метод перемешивает массив случайным образом
    private void mix(int[] a) {
        Random rnd = new Random();
        for (int i = 1; i < a.length; i++) {
            int j = rnd.nextInt(i);
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    // Метод возвращает количество букв с которого будет начитаться новое слово (зависит от level)
    private int howLetter(int maxLength) {
        Random random = new Random();
        int r = random.nextInt(100) + 1;
        int countLetter = 0;

        switch (level) {
            case 1:
                if (r <= 60) {
                    countLetter = 1;
                } else if (r <= 85) {
                    countLetter = 2;
                } else if (r <= 95) {
                    countLetter = 3;
                } else if (r <= 100) {
                    countLetter = 4;
                }
                break;

            case 2:
                if (r <= 60) {
                    countLetter = 2;
                } else if (r <= 85) {
                    countLetter = 1;
                } else if (r <= 95) {
                    countLetter = 3;
                } else if (r <= 100) {
                    countLetter = 4;
                }
                break;

            case 3:
                if (r <= 60) {
                    countLetter = 3;
                } else if (r <= 85) {
                    countLetter = 2;
                } else if (r <= 95) {
                    countLetter = 1;
                } else if (r <= 100) {
                    countLetter = 4;
                }
                break;
        }

        if (countLetter > maxLength) {
            countLetter = 2;
        }

        return countLetter;
    }
}
