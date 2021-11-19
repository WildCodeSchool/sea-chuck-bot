package dev.chuckbot;

import dev.chuckbot.entities.Joke;
import org.junit.jupiter.api.*;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JokesFilePersistenceTest {

    @Test
    @Order(1)
    void loadDataImportJokes() {
        // In case, a valid import source with only valid strings and seperators in each row, is handed over, import all lines
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes.txt"));
        assertEquals(6, persistence.loadData().size());
    }

    @Test
    @Order(2)
    void loadDataFileExtension() {
        // In case, that a file, which should be used as an import source, is no valid import source (reading lines not possible), hand over an empty arraylist
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/orange.png"));
        assertEquals(0, persistence.loadData().size());
    }

    @Test
    @Order(3)
    void loadDataEmptyLines() {
        // In case, that a row of the source file contains no values, don't import this record
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_EmptyLines.txt"));
        assertEquals(6, persistence.loadData().size());
    }

    @Test
    @Order(4)
    void loadDataFileExists() {
        // In case, that a file, which contains just an empty row should be used as an import source, hand over an emtpy arraylist
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_EmptyLines1.txt"));
        assertEquals(0, persistence.loadData().size());
    }

    @Test
    @Order(5)
    void loadDataClearFile() {
        // In case, that an emtpy file (no byte in file) should be used as an import source, hand over an emtpy arraylist
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBejokes_ClearFile"));
        assertEquals(0, persistence.loadData().size());
    }

    @Test
    @Order(6)
    void loadDataWrongDateFormat() {
        // In case, that after the first seperator no valid date is located, don't import this record
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_DateFormat.txt"));
        assertEquals(5, persistence.loadData().size());
    }

    @Test
    @Order(7)
    void loadDataSeparatorMissingDateNULL() {
        // In case, that after the first seperator no value is located (empty/null), don't import this record
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_DateFormatMissingDate.txt"));
        assertEquals(5, persistence.loadData().size());
    }

    @Test
    @Order(8)
    void loadDataSeparatorMissingDateUnidentifiedString() {
        // In case, that after the first seperator no value is located (empty/null), don't import this record
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_DateFormatMissingDateUnidentifiedString.txt"));
        assertEquals(5, persistence.loadData().size());
    }


    @Test
    @Order(9)
    void loadDataSeparator() {
        // In case, that a valid source file is handed over, which contains rows with more than one seperator, don't import this records
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokes_Separator.txt"));
        assertEquals(5, persistence.loadData().size());
    }


    @Test
    @Order(10)
    void storeData() {
        // Store all records if a list which contains valid joke-objects to a specific destination file, whereby the destination file already exists
        JokesPersistence persistence = new JokesFilePersistence(new File("src/test/resources/RiBeJokesLoadData.txt"));
        List<Joke> importedJokeList = persistence.loadData();

        importedJokeList.add(new Joke("Chuck Norris wurde gestern geblitzt – beim Ausparken.", LocalDate.now()));
        persistence.storeData(importedJokeList);
        assertEquals(7, persistence.loadData().size());

        // TODO: This is a "cleanUP" procedure and therefore should be put to the cleanUp-method
        importedJokeList.remove(importedJokeList.size()-1);
        persistence.storeData(importedJokeList);
    }

    @Test
    @Order(11)
    void storeDataFileNotExist() {
        // Store all records of a list which contains valid joke-objects to a specific destination file, whereby the destination file doesn't exist so far
        File myFile = new File("src/test/resources/michgibtesnicht.txt");
        JokesPersistence persistence = new JokesFilePersistence(myFile);

        List<Joke> newJokeList = new ArrayList<>();
        newJokeList.add(new Joke("Chuck Norris wurde gestern geblitzt – beim Einparken.", LocalDate.now()));
        persistence.storeData(newJokeList);

        assertEquals(1, persistence.loadData().size());
    }

    @Test
    @Disabled
    void storeDataSmart_FilePersistence() {
        List<Joke> list = new ArrayList<>();
        list.add(new Joke("Eldor findet meinen Witz besonders fantasielos.", LocalDate.now()));
        JokesPersistence pers = new JokesFilePersistence(new File("src/test/resources/RiBejokes.txt"));

        Exception e = Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            pers.storeDataSmart(list);
        });

        assertEquals(e.getMessage(), "Methode nicht implementiert");
    }



    @AfterAll
    public static void cleanUp() {
        System.out.println("Running: cleanUp");
        File myFile = new File("src/test/resources/michgibtesnicht.txt");
        if (myFile.delete()) {
            System.out.println("cleanUp: File deleted successfully");
        } else {
            System.err.println("cleanUp: Failed to delete the file");
        }
    }
}
