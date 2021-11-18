package dev.chuckbot.controller;


import dev.chuckbot.entities.Joke;
import dev.chuckbot.repository.JokesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class RESTController {

    @Autowired
    private JokesRepository repository;

    private List<Joke> jokeList;

    @GetMapping("/api/jokes/random")
    public Joke randomJoke(){

        jokeList = repository.findAll();
        Random random = new Random();

        Joke joke = new Joke("Chuck Norris ist so krass, er kann Witze aus leeren Datenbanken ziehen", LocalDate.now());

        if(!jokeList.isEmpty()){
            int index = random.nextInt(jokeList.size());
            return jokeList.get(index);
        }else{
            return joke;
        }
    }


}
