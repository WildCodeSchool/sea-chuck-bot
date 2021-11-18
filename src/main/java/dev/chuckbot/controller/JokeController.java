package dev.chuckbot.controller;

import dev.chuckbot.dto.JokeDTO;
import dev.chuckbot.entities.Joke;
import dev.chuckbot.repository.JokesRepository;
import dev.chuckbot.util.CreationDateComparator;
import dev.chuckbot.util.JokeTextComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    // Index- and Landing-Page
    // Methods reads all jokes from database, adds them to the model and returns home.html
    @GetMapping({"/", "/alljokes"})
    public String displayIndex(Model model, @RequestParam(required = false, defaultValue = "") String sort) {
        List<Joke> jokeList = repository.findAll();

        if (sort.equals("date")) {
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            model.addAttribute("jokes", jokeList);
        } else if (sort.equals("alphabetically")) {
            Collections.sort(jokeList, new JokeTextComparator());
            model.addAttribute("jokes", jokeList);
        } else {
            model.addAttribute("jokes", jokeList);
        }

        return "/home";
    }

    // GET: Add new jokes (dialogue)
    // Methods returns the form to add new jokes
    @GetMapping("/addNewJokeForm")
    public String addNewJoke(Model model){

        return "addNewJokeForm";

    }


    // POST: Add new jokes (safe)
    // Methods reveices joke-data through a dto-object from the model and safes joke to database
    @PostMapping("/addNewJoke")
    public String addNewJoke(@ModelAttribute JokeDTO j) {

        Joke joke = new Joke(j.getJokeText(), j.getCreationDate());
        repository.save(joke);
        return "redirect:/alljokes";

    }


    // Delete joke
    // Methods deletes a joke of the list from the database und redirects to index-/landing-page
    @GetMapping("/delete")
    public String deleteJoke(@RequestParam int id) {

        repository.deleteById(id);
        return "redirect:/";

    }

}
