package dev.chuckbot.controller;

import dev.chuckbot.Joke;
import dev.chuckbot.JokesFilePersistence;
import dev.chuckbot.JokesPersistence;
import dev.chuckbot.service.ChuckNorrisJokeService;
import dev.chuckbot.util.CreationDateComparator;
import dev.chuckbot.util.JokeTextComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ChuckJokeController {

    @GetMapping("/alljokes")
    public String displayAllJoke(Model model, @RequestParam String sort){

        File f = new File("src/test/resources/RiBeJokes.txt");
        JokesPersistence jp = new JokesFilePersistence(f);
        ChuckNorrisJokeService jokeService = new ChuckNorrisJokeService(jp);
        jokeService.initialize();
        List<Joke> jokeList = jokeService.getAllJokes();

        if(sort.equals("date")){
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            model.addAttribute("jokes", jokeList);
        }else if(sort.equals("alphabetically")){
            Collections.sort(jokeList, new JokeTextComparator());
            model.addAttribute("jokes", jokeList);
        }else{
            model.addAttribute("jokes", jokeList);
        }

        return "/home.html";
    }

    @PostMapping(value="/addNewJoke")
    public String addNewJoke(@RequestParam Map<String,String> allParams){
        System.out.println(allParams);
        return "addNewJokeForm.html";
    }

    @GetMapping("/addNewJokeForm")
    public String addNewJoke(){
        return "addNewJokeForm.html";
    }
}
