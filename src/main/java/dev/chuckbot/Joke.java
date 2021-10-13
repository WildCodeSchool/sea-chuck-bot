package dev.chuckbot;

import java.time.LocalDate;

public class Joke{
    // attributes
    private String jokeText;
    private LocalDate creationDate;

    public Joke(String jokeText, LocalDate creationDate) {
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }

    public String getJokeText() {
        return jokeText;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
}
