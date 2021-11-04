package dev.chuckbot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        final String URL = "jdbc:mysql://localhost/chuckbot";
        final String USER = "chuck";
        final String PASSWORD = "$Norris2015";

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


        } catch (SQLException e) {
            e.printStackTrace();
        }



    }


}
