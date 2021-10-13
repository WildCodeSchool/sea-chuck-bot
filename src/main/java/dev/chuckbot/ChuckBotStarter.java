package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;

import java.io.File;
import java.util.Set;

public class ChuckBotStarter {

    public static void main(String[] args) {
        File f = new File(args[0]);
        JokesPersistence filePersistence = new JokesFilePersistence(f);

        ChuckNorrisJokeService service = new ChuckNorrisJokeService(filePersistence);
        service.initialize();

        if (args.length > 0) {
            switch (args[0]) {
                case "print": service.printAllJokes(); break; // print all jokes
                case "add": break; // import jokes
                case "shutdown": service.shutdown(); break; // shutdown Chuck Norris Joke Service
                default:
                    System.out.println("Befehl unbekannt.");
            }
        } else {
            System.out.println("Keine Argumente vorhanden.");
        }
    }

}
