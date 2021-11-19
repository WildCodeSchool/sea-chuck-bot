package dev.chuckbot.util;

import dev.chuckbot.entities.Joke;

import java.time.LocalDate;
import java.util.Comparator;

@Deprecated
// Deprecated by Team EMC @ 2021-11-18 with task#47
public class CreationDateComparator implements Comparator<Joke> {
    @Deprecated
    @Override
    public int compare(Joke joke1, Joke joke2) {
        LocalDate joke1CreationDate = joke1.getCreationDate();
        LocalDate joke2CreationDate = joke2.getCreationDate();

        return joke1CreationDate.compareTo(joke2CreationDate);
    }
}
