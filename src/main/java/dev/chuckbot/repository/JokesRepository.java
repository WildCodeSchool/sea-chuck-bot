package dev.chuckbot.repository;

import dev.chuckbot.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JokesRepository extends JpaRepository<Joke, Integer> {
}
