package dev.chuckbot.controller;


import dev.chuckbot.entities.Joke;
import dev.chuckbot.service.ChuckNorrisJokeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@RestController
public class RESTController {

    private final ChuckNorrisJokeService service;

    public RESTController(ChuckNorrisJokeService service) {
        this.service = service;
    }

    @GetMapping("/api/jokes/random")
    public Joke randomJoke(){

        List<Joke> jokeList = service.getAllJokes();
        if (!jokeList.isEmpty()){
            Random random = new Random();
            int index = random.nextInt(jokeList.size());
            return jokeList.get(index);
        } else {
            Joke joke = new Joke("Chuck Norris ist so krass, er kann Witze aus leeren Datenbanken ziehen", LocalDate.now());
            return joke;
        }

    }

}
