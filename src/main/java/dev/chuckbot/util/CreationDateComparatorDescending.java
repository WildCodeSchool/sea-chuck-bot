package dev.chuckbot.util;

import dev.chuckbot.Joke;

import java.time.LocalDate;
import java.util.Comparator;

public class CreationDateComparatorDescending implements Comparator<Joke> {
    @Override
    public int compare(Joke joke1, Joke joke2) {
        LocalDate joke1CreationDate = joke1.getCreationDate();
        LocalDate joke2CreationDate = joke2.getCreationDate();

        return joke2CreationDate.compareTo(joke1CreationDate);
    }
}
