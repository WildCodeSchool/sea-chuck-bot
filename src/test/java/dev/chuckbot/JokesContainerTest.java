package dev.chuckbot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JokesContainerTest {

    @Test
    public void testAddNewJoke() {
        // Arrange
        JokesContainer jokesContainer = new JokesContainer();
        assertEquals(3, jokesContainer.numberOfJokes());

        // Act
        jokesContainer.addJoke("new joke");

        // Assert
        assertEquals(4, jokesContainer.numberOfJokes());
    }

    @Test
    public void testRemoveLastJoke() {
        // Arrange
        JokesContainer jokesContainer = new JokesContainer();
        assertEquals(3, jokesContainer.numberOfJokes());

        // Act
        jokesContainer.removeLastJoke();

        // Assert
        assertEquals(2, jokesContainer.numberOfJokes());
    }


}
