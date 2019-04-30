package me.geek.tom.chatministrator.util;

import java.util.List;

public class Util {

    public static int capsScore(String data) {
        int capsCount = 0;
        int lowerCount = 0;
        for (int i = 0; i < data.length(); i++) {
            char c = data.charAt(i);
            if (Character.isUpperCase(c)) {
                capsCount++;
            } else if (Character.isLowerCase(c)) {
                lowerCount++;
            }
        }

        int score;

        if (lowerCount == 0) {
            score = 10000;
        } else {
            try {
                score = capsCount / lowerCount;
            } catch (ArithmeticException e) {
                score = 10000;
            }
        }
        return score;
    }
}
