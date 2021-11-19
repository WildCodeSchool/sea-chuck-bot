package dev.chuckbot.util;

import dev.chuckbot.entities.Joke;
import java.util.List;

public class ChuckPrint {
    public static void printAllJokes(List<Joke> allJokes) {
        for (Joke joke : allJokes) {
            System.out.println(joke);
        }
    }

    public static void printAllJokesAtStart(List<Joke> allJokes) {
        System.out.println("These are the currently stored jokes");
        printAllJokes(allJokes);
    }
}
