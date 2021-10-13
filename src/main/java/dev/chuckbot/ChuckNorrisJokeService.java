package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ChuckNorrisJokeService {

    List<Joke> jokeList = new List<Joke>();


    public void initialize() {
        //Team RiBe liefert Methode mit Set als Rückgabewert. Set enthält alle Jokes aus txt-Datei.
    }
    public void printAllJokes() {
        //printed alle Jokes - wird noch eine String-Set erwartet oder ein Set vom Typ Joke? Wir gehen davon aus, dass das auf Joke geändert wird.
        Set<Joke> jokeSet = new HashSet<Joke>(jokeList);
        ChuckPrint.printAllJokes(jokeSet);
    }
    public void getNewestJoke() {
        // Team MDR stellt Methode zur Verfügung, der wir die unsortierte Liste übergeben und durch die Methode sortiert zurückgegeben wird.
    }

    public void addNewJoke(Joke joke) {
        // Neues Objekt vom Typ Joke wird erstellt und der Liste hinzugefügt.
        jokeList.add(joke);
    }

    public void shutdown() {
        //Team RiBe liefert Methode, der wir eine Liste übergeben können. Inhalt der Liste wird in txt-Datei geschrieben.
    }
}
