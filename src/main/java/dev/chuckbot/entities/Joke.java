package dev.chuckbot.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Joke {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jokeText;
    private LocalDate creationDate;

    // Constructors
    public Joke(){}

    public Joke(String jokeText, LocalDate creationDate) {
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }

    public Joke(int id, String jokeText, LocalDate creationDate) {
        this.id = id;
        this.jokeText = jokeText;
        this.creationDate = creationDate;
    }


    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


    //Helpers and overidden methods
    @Override
    public String toString() {
        return "Joke{" +
                "jokeText='" + jokeText + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
