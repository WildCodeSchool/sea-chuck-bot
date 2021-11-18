package dev.chuckbot.util;

import dev.chuckbot.entities.Joke;

import java.util.Comparator;

public class JokeTextComparator implements Comparator<Joke> {
    @Override
    public int compare(Joke o1, Joke o2) {
        return o1.getJokeText().compareTo(o2.getJokeText());
    }
}
