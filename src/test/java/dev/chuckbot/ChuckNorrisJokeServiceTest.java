package dev.chuckbot;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ChuckNorrisJokeServiceTest {

    @Test
    public void testInitialize() {
        File file = new File("src/test/resources/initializeTest.txt");
        JokesFilePersistence filePersistence = new JokesFilePersistence(file);
        List<Joke> jokeList  = filePersistence.loadData();
        assertTrue(jokeList.size()>= 1);
    }


    @Test
    public void testAddNewJokes() {
        File file = new File("src/test/resources/initializeTest.txt");
    }

    @Test
    public void testShutdown() {

    }
}
