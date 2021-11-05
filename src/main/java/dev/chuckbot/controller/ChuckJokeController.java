package dev.chuckbot.controller;

import dev.chuckbot.Joke;
import dev.chuckbot.JokesFilePersistence;
import dev.chuckbot.JokesPersistence;
import dev.chuckbot.service.ChuckNorrisJokeService;
import dev.chuckbot.util.CreationDateComparator;
import dev.chuckbot.util.JokeTextComparator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Collections;
import java.util.List;

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

    @PostMapping(value="/addNewJoke",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addNewJoke(MultiValueMap paramMap){
        System.out.println(paramMap);
        return "addNewJokeForm.html";
    }

    @GetMapping("/addNewJokeForm")
    public String addNewJoke(){
        return "addNewJokeForm.html";
    }
}
