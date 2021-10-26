package dev.chuckbot.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PreparedStatementJokeDao {

    private final Connection connection;

    public PreparedStatementJokeDao(Connection connection) {
        this.connection = connection;
    }

    public Optional<JokeEntity> getById(int id) throws SQLException {
        String query = "SELECT JOKETEXT, ADDED FROM JOKES WHERE id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.first()) {
            JokeEntity result = new JokeEntity(resultSet.getString("joketext"), resultSet.getDate("added").toLocalDate());
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public void insert(JokeEntity jokeEntity) throws SQLException {
        String query = "INSERT INTO JOKES(JOKETEXT, ADDED) VALUES( ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, jokeEntity.getJokeText());
        preparedStatement.setDate(2, Date.valueOf(jokeEntity.getAdded()));
        preparedStatement.executeUpdate();
    }

    public void insert(List<JokeEntity> jokeEntities) throws SQLException {
        String query = "INSERT INTO JOKES(JOKETEXT, ADDED) VALUES( ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        for (JokeEntity jokeEntity : jokeEntities) {
            preparedStatement.setString(1, jokeEntity.getJokeText());
            preparedStatement.setDate(2, Date.valueOf(jokeEntity.getAdded()));
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
    }

    public void deleteById(int id) throws SQLException {
        String query = "DELETE FROM JOKES WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }

    public List<JokeEntity> getAll() throws SQLException {
        String query = "SELECT JOKETEXT, ADDED FROM JOKES";

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        List<JokeEntity> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(new JokeEntity(resultSet.getString("JOKETEXT"), resultSet.getDate("ADDED").toLocalDate()));
        }
        return result;
    }
}