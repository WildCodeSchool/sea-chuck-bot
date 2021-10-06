package dev.chuckbot.util;

import java.util.HashSet;

public class ChuckPrint {
    public static void printAllJokes(HashSet<String> allJokes) {
        for (String joke : allJokes) {
            System.out.println(joke);
        }
    }

    public static void printAllJokesAtStart(HashSet<String> allJokes) {
        System.out.println("These are the currently stored jokes");
        printAllJokes(allJokes);
    }
}
