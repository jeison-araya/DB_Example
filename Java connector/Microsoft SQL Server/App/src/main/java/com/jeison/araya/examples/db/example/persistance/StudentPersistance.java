package com.jeison.araya.examples.db.example.persistance;

import com.jeison.araya.examples.db.example.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * This implementation is connected to a Data Base.
 */
public class StudentPersistance<S, K> implements Persistance<S, K> {
    private static Connection connection;


    public StudentPersistance() {
        try {
            refreshConnection();
        } catch (PersistanceException ignored) {

        }
    }

    private void refreshConnection() throws PersistanceException {
        try {
            connection = ConnectionDB.getConnection();
            if (connection == null)
                throw new PersistanceException("Error de conexión");
            System.out.println("Conexión activa...");
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new PersistanceException(throwables.getMessage());
        }
    }

    @Override
    public void create(S student) throws PersistanceException {
        throw new UnsupportedOperationException("Not finished");
    }

    @Override
    public S read(K key) throws PersistanceException {
        throw new UnsupportedOperationException("Not finished");
    }

    @Override
    public List<S> read() throws PersistanceException {
        ResultSet resultSet;
        // Execute statement...

        String values = "";
        try {
            resultSet = execute("select * from participantes");
            while (resultSet.next()) {
                values += "Nombre: \t" + resultSet.getString("nombre") + "\t\t"
                        + "Apellido: \t" + resultSet.getString("apellido") + "\t\t"
                        + "Carné: \t" + resultSet.getString("carne") + "\n";
            }
            System.out.println(values);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(S student) throws PersistanceException {
        throw new UnsupportedOperationException("Not finished");
    }

    @Override
    public void delete(S student) throws PersistanceException {
        throw new UnsupportedOperationException("Not finished");
    }


    /**
     * Executes an stament and return the result of the excecution.
     *
     * @param statement
     * @return {@code ResultSet} result set response.
     * @throws PersistanceException When an error with the connection ocurrs.
     */
    public ResultSet execute(String statement) throws PersistanceException {
        if (connection == null)
            throw new PersistanceException("Error con la conexión");

        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            // Prepare statement...
            preparedStatement = connection.prepareStatement(statement);
            // Execute statement...
            resultSet = preparedStatement.executeQuery();
            System.out.println("Statement excecuted: " + statement);
            return resultSet;
        } catch (Exception e) {
            throw new PersistanceException(e.getMessage());
        }
    }
}

