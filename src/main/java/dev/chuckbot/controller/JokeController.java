package dev.chuckbot.controller;

import dev.chuckbot.entities.Joke;
import dev.chuckbot.repository.JokesRepository;
import dev.chuckbot.util.CreationDateComparator;
import dev.chuckbot.util.JokeTextComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class JokeController {

    @Autowired
    private JokesRepository repository;

    //CRUD

    //Read all jokes
    @GetMapping({"/", "/alljokes"})
    public String displayIndex(Model model, @RequestParam(required = false, defaultValue = "") String sort){
        List<Joke> jokeList = repository.findAll();
        //model.addAttribute("jokes", jokeList);
        sortJokes(model, sort, jokeList);
        return "/home.html";
    }

    //Add new joke
    @PostMapping(value="/addNewJoke")
    public String addNewJoke(@RequestParam Map<String,String> allParams){

        repository.save(new Joke(allParams.get("text"), LocalDate.parse(allParams.get("date"))));
        return "redirect:/";
    }

    @GetMapping("/addNewJokeForm")
    public String addNewJoke(Model model){
        model.addAttribute("dateNow", LocalDate.now());
        return "addNewJokeForm.html";
    }

    private void sortJokes(Model model, String sort, List<Joke> jokeList) {
        if(sort.equals("date")){
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            model.addAttribute("jokes", jokeList);
        }else if(sort.equals("alphabetically")){
            Collections.sort(jokeList, new JokeTextComparator());
            model.addAttribute("jokes", jokeList);
        }else{
            model.addAttribute("jokes", jokeList);
        }
    }
}
