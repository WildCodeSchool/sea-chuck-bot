package dev.chuckbot;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JokesFilePersistenceTest {

    @Test
    void loadDataImportJokes() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes.txt"));

        assertEquals(6, persistence.loadData().size());
    }

    @Test
    void loadDataFileExtension() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/orange.png"));

        assertEquals(0, persistence.loadData().size());
    }

    @Test
    void loadDataEmptyLines() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_EmptyLines.txt"));

        assertEquals(6, persistence.loadData().size());
    }

    @Test
    void loadDataFileExists() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_EmptyLines1.txt"));

        assertEquals(0, persistence.loadData().size());
    }

    @Test
    void loadDataClearFile() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_ClearFile"));

        assertEquals(0, persistence.loadData().size());
    }

    @Test
    void loadDataWrongDateFormat() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_DateFormat.txt"));

        assertEquals(0, persistence.loadData().size());
    }

    @Test
    void loadDataSeparator() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_Separator.txt"));

        assertEquals(5, persistence.loadData().size());
    }

    @Test
    void storeData() {
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokesLoadData.txt"));
        List<Joke> importedJokeList = persistence.loadData();

        Joke myFirstJoke = new Joke("Chuck Norris wurde gestern geblitzt – beim Einparken.", LocalDate.now());
        importedJokeList.add(myFirstJoke);


        persistence.storeData(importedJokeList);

        assertEquals(7, persistence.loadData().size());

        //importedJokeList.remove(importedJokeList.size()-1);
        importedJokeList.remove(6);
        persistence.storeData(importedJokeList);

    }
    @Test
    void storeDataFileNotExist(){
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/michgibtesnicht.txt"));
        List<Joke> newJokeList = new ArrayList<>();

        Joke myFirstJoke = new Joke("Chuck Norris wurde gestern geblitzt – beim Einparken.", LocalDate.now());
        newJokeList.add(myFirstJoke);


        persistence.storeData(newJokeList);

        assertEquals(1, persistence.loadData().size());

        //importedJokeList.remove(importedJokeList.size()-1);

    }
}