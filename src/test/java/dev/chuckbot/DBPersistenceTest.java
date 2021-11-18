package dev.chuckbot;

import dev.chuckbot.entities.Joke;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBPersistenceTest {
    static private Connection conn;

    @BeforeAll
    static void getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost/chuckbot";
        final String USER = "chuck";
        final String PASSWORD = "2508-Christiane!";

        conn = DriverManager.getConnection(URL,USER, PASSWORD);

        Statement s = conn.createStatement();
        s.execute("TRUNCATE JOKES");

        try(PreparedStatement ps = conn.prepareStatement("Insert into JOKES (JOKETEXT, ADDED) values (?,?)")){
            ps.setString(1,"Das ist unser Testjoke");
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    void loadData() {
        JokesPersistence myPers = new DBPersistence(conn);
        List<Joke> testJokes = myPers.loadData();

        assertTrue(testJokes.size()>0, "Die Datenbank sollte mindestens einen Eintrag enthalten.");
    }

    @Test
    void storeData() {
        List<Joke> list = new ArrayList<>();
        list.add(new Joke("Eldor findet meinen Witz fantasielos.", LocalDate.now()));
        JokesPersistence myPers = new DBPersistence(conn);

        myPers.storeData(list);
        assertEquals(1, myPers.loadData().size());
    }

    @Test
    void storeDataSmart() {

        List<Joke> list = new ArrayList<>();
        list.add(new Joke("Eldor findet meinen Witz besonders fantasielos.", LocalDate.now()));
        JokesPersistence myPers = new DBPersistence(conn);

        myPers.storeDataSmart(list);
        assertEquals(2, myPers.loadData().size());
    }

    @AfterAll
    static void closeConnection() throws SQLException {
        conn.close();
    }
}