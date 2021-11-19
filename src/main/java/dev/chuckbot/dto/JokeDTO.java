package dev.chuckbot.dto;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class JokeDTO {

    // TODO: Team MDR und MAC informieren, dass Joke angepasst werden muss!

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