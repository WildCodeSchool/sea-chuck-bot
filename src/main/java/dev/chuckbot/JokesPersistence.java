package dev.chuckbot;

import dev.chuckbot.entities.Joke;

import java.util.List;

@Deprecated
// Deprecated by Team EMC @ 2021-11-18 with task#47
public interface JokesPersistence {

    @Deprecated
    List<Joke> loadData();

    @Deprecated
    /**
     * Will be succeeded by Smart Storage storeDataSmart
     */
    void storeData(List<Joke> jokes);

    @Deprecated
    void storeDataSmart(List<Joke> jokes);

    @Deprecated
    void deleteByID(int id);

}
