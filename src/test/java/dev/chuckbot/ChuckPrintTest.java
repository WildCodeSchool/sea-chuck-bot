package dev.chuckbot;

import dev.chuckbot.util.ChuckPrint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChuckPrintTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private HashSet<String> jokes = new HashSet<>();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        jokes.add("Witz1");
        jokes.add("Witz2");
    }

    @Test
    public void testPrintAllJokes() {
        ChuckPrint.printAllJokes(jokes);
        assertTrue(outContent.toString().matches("(Witz1\\nWitz2\\n|Witz2\\nWitz1\\n)"));
    }

    @Test
    public void testPrintAllJokesAtStart() {
        ChuckPrint.printAllJokesAtStart(jokes);
        assertTrue(outContent.toString().matches("These are the currently stored jokes\\n(Witz1\\nWitz2\\n|Witz2\\nWitz1\\n)"));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
