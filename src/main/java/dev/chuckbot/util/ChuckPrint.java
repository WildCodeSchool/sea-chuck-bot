package dev.chuckbot.util;

import java.util.List;

public class ChuckPrint {
    public static void printAllJokes(List<String> allJokes) {
        for (String joke : allJokes) {
            System.out.println(joke);
        }
    }

    public static void printAllJokesAtStart(List<String> allJokes) {
        System.out.println("These are the currently stored jokes");
        printAllJokes(allJokes);
    }
}
