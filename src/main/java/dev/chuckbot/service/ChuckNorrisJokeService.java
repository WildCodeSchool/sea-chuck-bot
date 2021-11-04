package dev.chuckbot.service;

import dev.chuckbot.Joke;
import dev.chuckbot.JokesPersistence;
import dev.chuckbot.util.ChuckPrint;
import dev.chuckbot.util.CreationDateComparator;

import java.io.File;
import java.util.*;

public class ChuckNorrisJokeService {

    private final JokesPersistence persistence;

    public ChuckNorrisJokeService(JokesPersistence persistence) {
        this.persistence = persistence;
    }

    public List<Joke> jokeList = new ArrayList<Joke>();

    public void initialize() {
        //Team RiBe liefert Methode mit Set als Rückgabewert. Set enthält alle Jokes aus txt-Datei.
        jokeList = persistence.loadData();
    }

    public void printAllJokes() {
        //printed alle Jokes - wird noch eine String-Set erwartet oder ein Set vom Typ Joke? Wir gehen davon aus, dass das auf Joke geändert wird.
        List<Joke> jokeSet = new ArrayList<>(jokeList);
        ChuckPrint.printAllJokes(jokeSet);
    }

    public Joke getNewestJoke() {
        // Team MDR stellt Methode zur Verfügung, der wir die unsortierte Liste übergeben und durch die Methode sortiert zurückgegeben wird.

        Collections.sort(jokeList, new CreationDateComparator());

        return jokeList.get(jokeList.size()-1);
    }

    public List getAllJokes(){
        return jokeList;
    }

    public void addNewJoke(Joke joke) {
        // Neues Objekt vom Typ Joke wird erstellt und der Liste hinzugefügt.
        jokeList.add(joke);
    }

    public void shutdown() {
        //Team RiBe liefert Methode, der wir eine Liste übergeben können. Inhalt der Liste wird in txt-Datei geschrieben.
        persistence.storeData(jokeList);

    }
}
