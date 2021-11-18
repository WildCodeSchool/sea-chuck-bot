package dev.chuckbot;

import dev.chuckbot.entities.Joke;
import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {

        final String URL = "jdbc:mysql://localhost/chuckbot";
        final String USER = "chuck";
        final String PASSWORD = "2508-Christiane!";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection(((url + schema)), user, password);
            Connection conn = DriverManager.getConnection(URL,USER, PASSWORD);
            JokesPersistence myPers = new DBPersistence(conn);
            List<Joke> testJokes = myPers.loadData();
            for(Joke testJ : testJokes){
                System.out.println(testJ);
            }


            myPers.storeData(testJokes);

            testJokes.add(new Joke("Test Joke", LocalDate.now()));
            myPers.storeDataSmart(testJokes);




        } catch (SQLException e) {
            e.printStackTrace();
        }

        File f = new File("src/test/resources/RiBejokes.txt");
        JokesPersistence filePersistence = new JokesFilePersistence(f);
        filePersistence.storeDataSmart(filePersistence.loadData());

    }


}
