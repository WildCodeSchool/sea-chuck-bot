package dev.chuckbot;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBPersistence implements JokesPersistence {

    private final Connection conn;

    public DBPersistence(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Joke> loadData() {

        List<Joke> dbListJokes = new ArrayList<>();
        try (Statement statJokes = conn.createStatement();
             ResultSet jokesResultSet = statJokes.executeQuery("SELECT * FROM JOKES")) {
            while (jokesResultSet.next()) {
                dbListJokes.add(new Joke(jokesResultSet.getInt(1), jokesResultSet.getString(2), LocalDate.parse(jokesResultSet.getString(3))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dbListJokes;
    }

    @Override
    public void storeData(List<Joke> jokes) {

        try {
            Statement truncStatement = conn.createStatement();
            truncStatement.executeUpdate("TRUNCATE JOKES");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Joke thisJoke : jokes) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO JOKES (JOKETEXT, ADDED) VALUES (?, ?)")) {

                Date myDate = Date.valueOf(thisJoke.getCreationDate());
                ps.setString(1, thisJoke.getJokeText());
                ps.setDate(2, myDate);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void storeDataSmart(List<Joke> jokes) {


        for(Joke j: jokes){
            try(PreparedStatement ps = conn.prepareStatement("Select * from JOKES where JOKETEXT=?")){
                ps.setString(1, j.getJokeText());
                ResultSet rs = ps.executeQuery();

                if(!rs.next()){
                    try(PreparedStatement psInsert = conn.prepareStatement("Insert into JOKES (JOKETEXT, ADDED) Values (?,?)")){
                        psInsert.setString(1, j.getJokeText());
                        psInsert.setDate(2, Date.valueOf(j.getCreationDate()));
                        psInsert.execute();
                        System.out.println("Eintrag added.");
                    }catch(SQLException e){
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("Eintrag mit ID " + j.getId() + " bereits vorhanden.");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void deleteByID(int id){

        try(PreparedStatement ps = conn.prepareStatement("delete from JOKES where id=?")){
            ps.setInt(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
