package dev.chuckbot.persistence;

import dev.chuckbot.Joke;
import dev.chuckbot.JokesPersistence;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JokesDBPersistence implements JokesPersistence {

    private final PreparedStatementJokeDao preparedStatementJokeDao;

    public JokesDBPersistence(PreparedStatementJokeDao preparedStatementJokeDao) {
        this.preparedStatementJokeDao = preparedStatementJokeDao;
    }

    @Override
    public List<Joke> loadData() {
        try {
            List<JokeEntity> entities = preparedStatementJokeDao.getAll();
            return entities.stream().map(it -> new Joke(it.getJokeText(), it.getAdded())).collect(Collectors.toList());
        } catch (Exception ex) {
            System.err.println("Oh no: " + ex.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public void storeData(List<Joke> jokes) {
        try {
            List<JokeEntity> entities = jokes.stream().map(it -> new JokeEntity(it.getJokeText(), it.getCreationDate())).collect(Collectors.toList());
            preparedStatementJokeDao.insert(entities);
        } catch (Exception ex) {
            System.err.println("Oh no: " + ex.getMessage());
        }
    }
}
