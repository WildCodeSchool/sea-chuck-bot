package dev.chuckbot.service;

import dev.chuckbot.entities.Joke;
import dev.chuckbot.repository.JokesRepository;
import dev.chuckbot.util.ChuckPrint;
import dev.chuckbot.util.CreationDateComparator;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChuckNorrisJokeService {

    private final JokesRepository persistence;

    public ChuckNorrisJokeService(JokesRepository persistence) {
        this.persistence = persistence;
    }

    public void printAllJokes() {
        //printed alle Jokes - wird noch eine String-Set erwartet oder ein Set vom Typ Joke? Wir gehen davon aus, dass das auf Joke geändert wird.
        ChuckPrint.printAllJokes(persistence.findAll());
    }

    public Joke getNewestJoke() {
        List<Joke> jokeList = persistence.findAll();
        Collections.sort(jokeList, new CreationDateComparator());
        return jokeList.get(jokeList.size()-1);
    }

    public List<Joke> getAllJokes(){
        return persistence.findAll();
    }

    public void addNewJoke(Joke joke) {
        persistence.saveAndFlush(joke);
    }

    public void deleteByID(int id){
        persistence.deleteById(id);
    }
}
