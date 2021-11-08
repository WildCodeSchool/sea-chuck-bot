package dev.chuckbot.controller;

import dev.chuckbot.*;
import dev.chuckbot.service.ChuckNorrisJokeService;
import dev.chuckbot.util.CreationDateComparator;
import dev.chuckbot.util.JokeTextComparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ChuckJokeController {

    private ChuckNorrisJokeService jokeService;

    // Das passiert EINMALIG!
    public ChuckJokeController() {
        try {
            //Muss entsprechend angepasst werden!!!!
            final String URL = "jdbc:mysql://localhost/chuckbot";
            final String USER = "chuck";
            final String PASSWORD = "2508-Christiane!";

            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            JokesPersistence myPers = new DBPersistence(conn);

            jokeService = new ChuckNorrisJokeService(myPers);
            jokeService.initialize();
        } catch (Exception ex) {
            System.err.println("Das war nix: " + ex.getMessage());
        }
    }

    // Das passiert bei jedem Aufruf
    @GetMapping("/alljokes")
    public String displayAllJoke(Model model, @RequestParam (required = false, defaultValue = "") String sort) {

        List<Joke> jokeList = jokeService.getAllJokes();

        if (sort.equals("date")) {
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            model.addAttribute("jokes", jokeList);
        } else if (sort.equals("alphabetically")) {
            Collections.sort(jokeList, new JokeTextComparator());
            model.addAttribute("jokes", jokeList);
        } else {
            model.addAttribute("jokes", jokeList);
        }

        return "/home.html";
    }

    @GetMapping("/addNewJokeForm")
    public String getJoke() {

        return "addNewJokeForm.html";
    }


    @PostMapping("/addNewJoke")
    public String addNewJoke(@ModelAttribute JokeDTO j) {

        Joke joke = new Joke(j.getJokeText(), j.getCreationDate());

        jokeService.addNewJoke(joke);
        jokeService.shutdown();
        return "redirect:/alljokes";

    }

    @GetMapping("/delete")
    public String deleteJoke(@RequestParam int id){
        jokeService.deleteByID(id);

        return "redirect:/alljokes";
    }

}


