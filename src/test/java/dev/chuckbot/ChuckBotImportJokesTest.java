package dev.chuckbot;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ChuckBotImportJokesTest {

    @Test
    void importJokes() {
        Set<String> test = ChuckBotImportJokes.importJokes("src/test/resources/jokes.txt");
        assertEquals(250,test.size());
    }
    @Test
    void importJokesFailFileExtension(){
        Set<String> test = ChuckBotImportJokes.importJokes("src/test/resources/orange.png");
        assertNull(test);
    }
    @Test
    void importJokesEmptyLines(){
        Set<String> test = ChuckBotImportJokes.importJokes("src/test/resources/jokesemptyline.txt");
        assertEquals(248,test.size());
    }
    @Test
    void importJokesFileExists(){
        Set<String> test = ChuckBotImportJokes.importJokes("src/test/resources/joker.txt");
        assertNull(test);
    }



}