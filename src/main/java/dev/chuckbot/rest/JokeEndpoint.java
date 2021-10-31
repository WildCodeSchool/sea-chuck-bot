package dev.chuckbot.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JokeEndpoint {

    @GetMapping("/jokes")
    List<String> all() {
        List<String> jokes = new ArrayList<>();
        jokes.add("joke1");
        return jokes;
    }

    @GetMapping("/jokes/random")
    String randomJoke() {
        return "Chuck Norris's brain waves are suspected to be harmful to cell phones.";
    }
}
