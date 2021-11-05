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

    public JokesFilePersistence(File storage) {
        this.storage = storage;
    }

    @Override
    public List<Joke> loadData() {
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
                    if (currentJoke.length == 2) {
                        if (!currentJoke[0].isEmpty()) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            jokeList.add(new Joke(currentJoke[0], LocalDate.parse(currentJoke[1], dtf)));
                        }
                    }
                }

                //Check if jokesList is empty
                if (jokeList.isEmpty()) {
                    System.err.println("File is empty. Couldn't import!");
                    return new ArrayList<>();
                } else {
                    System.out.println("import successful");
                    return jokeList;
                }
            } catch (MalformedInputException e) {
                System.err.println("Wrong file type!");
                return new ArrayList<>();
            } catch (DateTimeParseException e) {
                System.err.println("Wrong date format.");
                return new ArrayList<>();
            } catch (IOException e) {
                System.err.println("Import error!");
                return new ArrayList<>();
            }
        } else {
            System.err.println("Import error! File does not exist.");
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
            System.out.println("Jokes stored successfully!");

        } catch (IOException e) {
            System.err.println("Tried to export jokes but something went terribly wrong (this is no joke!)");
        }
    }

    @Override
    public void storeDataSmart(List<Joke> jokes) {
        throw new UnsupportedOperationException("Methode nicht implementiert");
    }
}
