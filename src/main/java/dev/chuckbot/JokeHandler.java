package dev.chuckbot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class JokeHandler {

    public static void addingNewJoke() {
        ArrayList<String> jokeList = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String weitererJoke = "y";

        while (weitererJoke.equals("y")) {
            System.out.println("Enter a joke: ");
            input = new Scanner(System.in);
            String jokeListe = input.nextLine();
            jokeList.add(jokeListe);
            System.out.println("Do you want to add another joke? y/n");
            Scanner input2 = new Scanner(System.in);
            weitererJoke = input2.nextLine();
        }
        input.close();

        File fileJokes = new File("src/resources/jokes.txt");
        if (!fileJokes.exists()) {
            try {
                fileJokes.createNewFile();
                Files.write(fileJokes.toPath(), jokeList.toString().getBytes());
            }
            catch (IOException e){
            }
        }
        else {
            try {
                Files.write(fileJokes.toPath(), jokeList.toString().getBytes());
            }
            catch (IOException e){
            }
        }


        for (String s : jokeList) {
            System.out.println("You have entered: " + s);
        }


    }

}
