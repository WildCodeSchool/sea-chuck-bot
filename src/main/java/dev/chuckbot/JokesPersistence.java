package dev.chuckbot;

import dev.chuckbot.entities.Joke;

import java.util.List;

public interface JokesPersistence {

    List<Joke> loadData();

    @Deprecated
    /**
     * Will be succeeded by Smart Storage storeDataSmart
     */
    void storeData(List<Joke> jokes);

    void storeDataSmart(List<Joke> jokes);

}
