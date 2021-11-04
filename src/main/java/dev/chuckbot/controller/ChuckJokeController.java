package dev.chuckbot.controller;

import dev.chuckbot.Joke;
import dev.chuckbot.JokesFilePersistence;
import dev.chuckbot.JokesPersistence;
import dev.chuckbot.service.ChuckNorrisJokeService;
import dev.chuckbot.util.CreationDateComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Controller
public class ChuckJokeController {

    @GetMapping("/alljokes")
    public String joke(Model m, @RequestParam String sort){

        File f = new File("src/test/resources/RiBeJokes.txt");
        JokesPersistence jp = new JokesFilePersistence(f);
        ChuckNorrisJokeService jokeService = new ChuckNorrisJokeService(jp);
        jokeService.initialize();
        List<Joke> jokeList = jokeService.getAllJokes();

        if(sort.equals("newest")){
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            m.addAttribute("jokes", jokeList);
        }else{
            m.addAttribute("jokes", jokeList);
        }

        return "/home.html";
    }
}
