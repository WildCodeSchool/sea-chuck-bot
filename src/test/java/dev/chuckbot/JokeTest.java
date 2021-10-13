package dev.chuckbot;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JokeTest {

    @Test
    public void testJokeText() {
        // Arrange
        DateTimeFormatter testDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate sixthOfSeptember = LocalDate.parse("06.09.2021", testDateFormat);
        // Act
        Joke joke1test = new Joke("Joke1", sixthOfSeptember);
        // Assert
        assertEquals("Joke1", joke1test.getJokeText());
    }

    @Test
    public void testJokeCreationDate() {
        // Arrange
        DateTimeFormatter testDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate sixthOfSeptember = LocalDate.parse("06.09.2021", testDateFormat);
        LocalDate creationDate = LocalDate.now();
        // Act
        Joke joke2test = new Joke("Joke2", sixthOfSeptember);
        // Assert
        assertEquals(sixthOfSeptember, joke2test.getCreationDate());
    }
}
