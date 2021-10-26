package dev.chuckbot;

import dev.chuckbot.persistence.DatasourceFactory;
import dev.chuckbot.persistence.JokesDBPersistence;
import dev.chuckbot.persistence.PreparedStatementJokeDao;
import dev.chuckbot.service.MockedChatService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChuckBotStarter implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ChuckBotStarter.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (args.length > 1) {
            // Welche Datei wird als Persistenz genutzt?
            /*
            File f = new File(args[1]);
            JokesPersistence filePersistence = new JokesFilePersistence(f);
            */

            DatasourceFactory fac = new DatasourceFactory();
            PreparedStatementJokeDao preparedStatementJokeDao = new PreparedStatementJokeDao(fac.getConnection());
            JokesPersistence dbPersistence = new JokesDBPersistence(preparedStatementJokeDao);
            // Erstelle Service und übergebe Persistenz
            ChuckNorrisJokeService service = new ChuckNorrisJokeService(dbPersistence);

            // Initialisiere Service
            service.initialize();

            // Create Mocked Service
            MockedChatService mockedChatService = new MockedChatService("general", "#whataboutchuck", service);

            switch (args[0]) {
                case "print": service.printAllJokes(); break; // print all jokes
                case "newest":
                    System.out.println(service.getNewestJoke());
                    break; // import jokes
                case "shutdown": service.shutdown(); break; // shutdown Chuck Norris Joke Service
                case "transfer": service.shutdown(); break;
                case "message": mockedChatService.publishMessage(args[1]); break;
                default:
                    System.out.println("Befehl unbekannt.");
            }
        } else {
            System.out.println("Keine Argumente vorhanden.");
        }
    }

}
