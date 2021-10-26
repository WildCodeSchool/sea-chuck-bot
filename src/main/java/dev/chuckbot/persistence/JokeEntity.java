package dev.chuckbot.persistence;

import java.time.LocalDate;

public class JokeEntity {

    private LocalDate added;
    private String jokeText;

    public JokeEntity(String jokeText, LocalDate added) {
        this.jokeText = jokeText;
        this.added = added;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

}
