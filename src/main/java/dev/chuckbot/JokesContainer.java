package dev.chuckbot;

import java.util.ArrayList;
import java.util.List;

public class JokesContainer {

    private List<String> jokes;

    public JokesContainer() {
        jokes = new ArrayList<>();
        jokes.add("Chuck Norris kann Feuer mit einer Lupe machen – nachts!");
        jokes.add("Chuck Norris läuft 100 Meter in einer Sekunde. Er kennt immer eine Abkürzung.");
        jokes.add("Chuck Norris wurde gestern geblitzt – beim Einparken.");
    }

    public void removeLastJoke() {
        jokes.remove(jokes.size()-1);
    }

    public void addJoke(String joke) {
        jokes.add(joke);
    }

    public int numberOfJokes() {
        return jokes.size();
    }

    public void printJokes() {
        for (String joke: jokes ){
            System.out.println(joke);
        }
    }

}
