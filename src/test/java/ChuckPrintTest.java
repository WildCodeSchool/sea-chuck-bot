import dev.chuckbot.util.ChuckPrint;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChuckPrintTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private List<String> jokes = new ArrayList<>();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        jokes.add("Witz1");
        jokes.add("Witz2");
    }

    @Test
    public void testPrintAllJokes() {
        ChuckPrint.printAllJokes(jokes);
        assertEquals("Witz1\nWitz2\n", outContent.toString());
    }

    @Test
    public void testPrintAllJokesAtStart() {
        ChuckPrint.printAllJokesAtStart(jokes);
        assertEquals("These are the currently stored jokes\nWitz1\nWitz2\n", outContent.toString());
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}
