package dev.chuckbot.util;

import dev.chuckbot.Joke;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreationDateComparatorTest {
    @Test
    public void TestCreationDate() {
        // Arrange
        DateTimeFormatter testDateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate cd1 = LocalDate.parse("06.09.2021", testDateFormat);
        LocalDate cd2 = LocalDate.parse("01.01.1921", testDateFormat);
        LocalDate cd3 = LocalDate.parse("01.10.2021", testDateFormat);
        List<Joke> unsortedJokeList = new ArrayList<>();
        unsortedJokeList.add(new Joke("Joke1", cd1));
        unsortedJokeList.add(new Joke("Joke2", cd2));
        unsortedJokeList.add(new Joke("Joke3", cd3));
        // Act
        Collections.sort(unsortedJokeList, new CreationDateComparator());
        // Assert
        assertEquals(cd2, unsortedJokeList.get(0).getCreationDate());
    }
}