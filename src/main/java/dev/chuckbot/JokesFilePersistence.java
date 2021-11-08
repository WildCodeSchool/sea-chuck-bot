package dev.chuckbot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class JokesFilePersistence implements JokesPersistence {

    private final File storage;
    private final String className = this.getClass().getSimpleName();

    public JokesFilePersistence(File storage) {
        this.storage = storage;
    }

    @Override
    public List<Joke> loadData() {

        int rowsCount = 0;
        int rowsImported = 0;

        //created data structure
        List<Joke> jokeList = new ArrayList<>();

        //check if file exists
        if (storage.exists()) {

            try {
                //read file into List
                List<String> inputList = Files.readAllLines(storage.toPath());

                //Create List of Jokes
                for (String s : inputList) {
                    String[] currentJoke = s.split(";");
                    rowsCount++;

                    //Check,if the array has at least two colums (joke and date as String)
                    if (currentJoke.length == 2) {
                        //Check, if the field for the joke is not an emtpy string
                        if (!currentJoke[0].isEmpty()) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            //Check, if the field for the date contains a parsable dateformat
                            // and in case, import both, joke and date, into the list 'jokelist'
                            if (isParsableToLocaldate(currentJoke[1], dtf)){
                                jokeList.add(new Joke(currentJoke[0], LocalDate.parse(currentJoke[1], dtf)));
                                rowsImported++;
                            }
                        }
                    }
                }

                //Check if jokesList is empty
                if (jokeList.isEmpty()) {
                    System.err.println(className + " --- IMPORT FAILED --- No valid entries to import found in source file!");
                    return new ArrayList<>();
                } else {
                    System.out.println(className + " --- IMPORT SUCCESSFUL --- Imported " + rowsImported + " out of " + rowsCount + " jokes in source file!");
                    return jokeList;
                }
            } catch (MalformedInputException e) {
                System.err.println(className + " --- IMPORT FAILED --- Wrong file type!");
                return new ArrayList<>();
            } catch (DateTimeParseException e) {
                System.err.println(className + " --- IMPORT FAILED --- Wrong date format!");
                return new ArrayList<>();
            } catch (IOException e) {
                System.err.println(className + " --- IMPORT FAILED --- Something went completely wrong!");
                return new ArrayList<>();
            }
        } else {
            System.err.println(className + " --- IMPORT FAILED --- File does not exist!");
            return new ArrayList<>();
        }
    }

    @Override
    public void storeData(List<Joke> jokes) {
        // Write the list to a file, whereby the file is always regenerated
        Path out = this.storage.toPath();
        try (BufferedWriter writer = Files.newBufferedWriter(out, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (Joke currJoke : jokes) {
                //writer.append(currJoke.serializedCSV());
                writer.append(currJoke.getJokeText() + ";" + currJoke.getCreationDate() + "\n");
            }
            System.out.println(className + " --- STORE SUCCESSFUL --- Jokes stored successfully!");

        } catch (IOException e) {
            System.err.println(className + " --- STORE FAILED --- Tried to export jokes but something went terribly wrong (this is no joke!)");
        }
    }

    @Override
    public void storeDataSmart(List<Joke> jokes) {

    }

    // Helpermethod to check, wether the string in file could be parsed to localdate or not, based on the specified dateformat
    private static boolean isParsableToLocaldate(String dateStr, DateTimeFormatter dateFormatter) {
        try {
            LocalDate.parse(dateStr, dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    @Override
    public void deleteByID(int id) {

    }
}
