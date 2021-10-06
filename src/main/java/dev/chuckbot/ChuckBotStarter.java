package dev.chuckbot;

import java.io.File;
import java.util.List;

public class ChuckBotStarter {

    public static void main(String[] args) {
        for (String argument : args) {
            System.out.println(argument);
        }
        File inputWitze = new File("src/test/resources/witze.txt");
        // List<String> witzeListe = WitzeImporter.importKunden(inputWitze);


    }

}
