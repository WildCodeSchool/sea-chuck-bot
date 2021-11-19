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
    public String displayAllJoke(Model model, @RequestParam(required = false, defaultValue = "") String sort) {
        List<Joke> jokeList = jokeService.getAllJokes();

        sortJokes(model, sort, jokeList);
        return "/home.html";
    }

    private void sortJokes(Model model, String sort, List<Joke> jokeList) {
        if (sort.equals("date")) {
            Collections.sort(jokeList, new CreationDateComparator().reversed());
            model.addAttribute("jokes", jokeList);
        } else if (sort.equals("alphabetically")) {
            Collections.sort(jokeList, new JokeTextComparator());
            model.addAttribute("jokes", jokeList);
        } else {
            model.addAttribute("jokes", jokeList);
        }
    }

    @GetMapping("/showNewJokes")
    public String addNewJokeForm() {
        return "addNewJokeForm.html";
    }


    @PostMapping(value = "/addNewJoke")
    public String addNewJoke(@ModelAttribute JokeDTO joke) {

        LocalDate date = joke.getCreationDate();
        String jokeText = joke.getJokeText();
        Joke j = new Joke(jokeText, date);
        jokeService.addNewJoke(j);
        jokeService.shutdown();

        return "redirect:/alljokes";
    }

    @GetMapping(value = "/deleteById")
    public String deleteJoke(@RequestParam Long idJoke) {

        jokeService.deleteJoke(idJoke);
        // jokeService.shutdown();

        return "redirect:/alljokes";
    }

}
