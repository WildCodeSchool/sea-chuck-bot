package dev.chuckbot;

import java.time.LocalDate;

public class JokeDTO {
    private String jokeText;
    private LocalDate creationDate;

    public JokeDTO(String jokeText) {
        this.jokeText = jokeText;
        this.creationDate = LocalDate.now();
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

    /* public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    } */
}
