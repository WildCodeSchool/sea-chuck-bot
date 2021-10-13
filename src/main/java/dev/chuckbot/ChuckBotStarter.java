package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;

import java.util.Set;

public class ChuckBotStarter {

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "print": break; // print all jokes
                case "import": break; // import jokes
                case "shutdown": break; // shutdown Chuck Norris Joke Service
                default:
                    System.out.println("Befehl unbekannt.");
            }
        } else {
            System.out.println("Keine Argumente vorhanden.");
        }
    }

}
