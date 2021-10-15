package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;

import java.io.File;
import java.util.Set;

public class ChuckBotStarter {

    public static void main(String[] args) {
        if (args.length > 1) {
            // Welche Datei wird als Persistenz genutzt?
            File f = new File(args[1]);
            JokesPersistence filePersistence = new JokesFilePersistence(f);

            // Erstelle Service und übergebe Persistenz
            ChuckNorrisJokeService service = new ChuckNorrisJokeService(filePersistence);

            // Initialisiere Service
            service.initialize();

            switch (args[0]) {
                case "print": service.printAllJokes(); break; // print all jokes
                case "newest":
                    System.out.println(service.getNewestJoke());
                    break; // import jokes
                case "shutdown": service.shutdown(); break; // shutdown Chuck Norris Joke Service
                case "transfer": service.shutdown(); break;
                default:
                    System.out.println("Befehl unbekannt.");
            }
        } else {
            System.out.println("Keine Argumente vorhanden.");
        }
    }

}
