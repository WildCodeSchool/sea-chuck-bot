package dev.chuckbot.util;

import java.util.Set;

public class ChuckPrint {
    public static void printAllJokes(Set<String> allJokes) {
        for (String joke : allJokes) {
            System.out.println(joke);
        }
    }

    public static void printAllJokesAtStart(Set<String> allJokes) {
        System.out.println("These are the currently stored jokes");
        printAllJokes(allJokes);
    }
}
