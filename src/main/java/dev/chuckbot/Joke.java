package dev.chuckbot;

import java.time.LocalDate;

public class Joke {

    // TODO: Team MDR und MAC informieren, dass Joke angepasst werden muss!
    private int id;
    private String jokeText;
    private LocalDate creationDate;

    public Joke(String jokeText, LocalDate creationDate) {
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }

    public Joke(int id, String jokeText, LocalDate creationDate) {
        this.id = id;
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
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
