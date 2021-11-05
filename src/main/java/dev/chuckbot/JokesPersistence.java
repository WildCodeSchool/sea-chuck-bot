package dev.chuckbot;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public interface JokesPersistence {

    List<Joke> loadData();

    void storeData(List<Joke> jokes);

    void storeDataSmart(List<Joke> jokes) throws ExecutionControl.NotImplementedException;

}
