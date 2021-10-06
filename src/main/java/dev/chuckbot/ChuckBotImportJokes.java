package dev.chuckbot;

import java.io.File;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ChuckBotImportJokes {


    public static Set<String> importJokes(String pfad) {

        //created data structure
        File inputFile = new File(pfad);
        Set<String> jokeList = new HashSet<>();

        //check if file exists
        if (inputFile.exists()) {

            try {
                //read file into List
                List<String> inputList = Files.readAllLines(inputFile.toPath());

                if (!inputList.isEmpty()) {

                    //add List into HashSet
                    for (String s : inputList) {
                        //check if Strings are empty or null
                        if (!s.isBlank() || !s.isEmpty()) {
                            jokeList.add(s);
                        }

                    }
                    System.out.println("import successful");

                } else {
                    System.out.println("File is empty. Couldn't import!");
                    return null;
                }

            } catch (MalformedInputException e) {
                System.err.println("wrong file type!");
                return null;

            } catch (IOException e) {
                System.err.println("import error!");
                return null;
            }

            return jokeList;

        } else {
            System.err.println("import error");
            return null;
        }

    }

}

