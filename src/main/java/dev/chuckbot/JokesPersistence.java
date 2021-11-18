package dev.chuckbot;

import java.util.List;

public interface JokesPersistence {

    List<Joke> loadData();

    @Deprecated
    /**
     * Will be succeeded by Smart Storage storeDataSmart
     */
    void storeData(List<Joke> jokes);

    void storeDataSmart(List<Joke> jokes);

    void deleteJokeById(Long id);

}
