package dev.chuckbot;

import java.time.LocalDate;

public class Joke {

    private String jokeText;
    private LocalDate creationDate;

    public Joke(String jokeText, LocalDate creationDate) {
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }

    public String getJokeText() {
        return jokeText;
    }

    public void setJokeText(String jokeText) {
        this.jokeText = jokeText;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return "Joke{" +
                "jokeText='" + jokeText + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
