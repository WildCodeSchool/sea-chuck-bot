package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class ChuckBotStarter {

    public static void main(String[] args) throws IOException {
        /* for (String argument : args) {
            System.out.println(argument);
        }*/
        if (args.length > 0 && args[0].equals("print")) {

            File inputWitze = new File("src/test/resources/witze.txt");
            // List<String> witzeListe = WitzeImporter.importKunden(inputWitze);

            List<String> witzeListe = Files.readAllLines(inputWitze.toPath());
            ChuckPrint.printAllJokesAtStart(witzeListe);
        }
    }

}
