package dev.chuckbot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ChuckNorrisJokeServiceTest {

    private static ChuckNorrisJokeService chuckNorrisJokeService;

    @BeforeAll
    public static void setUp(){

        JokesPersistence jokesPersistence = new JokesFilePersistence(new File("src/test/resources/jokeServiceTest.txt"));
        chuckNorrisJokeService = new ChuckNorrisJokeService(jokesPersistence);
        chuckNorrisJokeService.initialize();

    }

    @Test
    public void testInitialize() {

        JokesPersistence jokesPersistence = new JokesFilePersistence(new File("src/test/resources/jokeServiceTest.txt"));
        chuckNorrisJokeService = new ChuckNorrisJokeService(jokesPersistence);
        chuckNorrisJokeService.initialize();

        assertTrue(chuckNorrisJokeService.jokeList.size()>= 1);

    }


    @Test
    public void testGetNewestJoke(){

        Joke joke1 = new Joke("Chuck Norris circumcised himself. At birth. With his bare hands.", LocalDate.of(2019,10,14));
        Joke joke2 = chuckNorrisJokeService.getNewestJoke();

        assertEquals(joke1.getCreationDate(), joke2.getCreationDate());
        assertEquals(joke1.getJokeText(), joke2.getJokeText());
    }


    @Test
    public void testAddNewJokes() {

        JokesFilePersistence filePersistence = new JokesFilePersistence(new File("src/test/resources/jokeServiceTest.txt"));
        chuckNorrisJokeService = new ChuckNorrisJokeService(filePersistence);
        chuckNorrisJokeService.initialize();
        int sizeBeforeAdding= chuckNorrisJokeService.jokeList.size();
        Joke joke1 = new Joke("Chuck Norris circumcised himself. At birth. With his bare hands.", LocalDate.of(2021,10,14));
        chuckNorrisJokeService.addNewJoke(joke1);
        int sizeAfterAdding = chuckNorrisJokeService.jokeList.size();

        assertTrue(sizeAfterAdding>sizeBeforeAdding);

    }

    @Test
    public void testShutdown() {

        JokesFilePersistence filePersistence = new JokesFilePersistence(new File("src/test/resources/jokeServiceShutdownTest.txt"));
        chuckNorrisJokeService = new ChuckNorrisJokeService(filePersistence);

        chuckNorrisJokeService.initialize();
        int sizeBeforeAddingAndShutdown = chuckNorrisJokeService.jokeList.size();

        Joke joke1 = new Joke("In the Beginning, there was nothing. Then Chuck Norris roundhouse kicked nothing and told it to get a job.", LocalDate.of(2019,10,14));
        chuckNorrisJokeService.addNewJoke(joke1);

        chuckNorrisJokeService.shutdown();

        chuckNorrisJokeService.initialize();
        int sizeAfterAddingAndShutdown = chuckNorrisJokeService.jokeList.size();

        assertTrue(sizeBeforeAddingAndShutdown<sizeAfterAddingAndShutdown);
    }
}
