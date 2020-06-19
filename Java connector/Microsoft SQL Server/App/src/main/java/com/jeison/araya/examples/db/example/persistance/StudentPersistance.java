package com.jeison.araya.examples.db.example.persistance;

import com.jeison.araya.examples.db.example.domain.Student;
import com.jeison.araya.examples.db.example.util.ConnectionDB;
import com.jeison.araya.examples.db.example.util.StudentBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation is connected to a Data Base.
 */
public class StudentPersistance implements Persistance<Student, String> {
    // Variables
    private static Connection connection;

    // Constructor \\
    public StudentPersistance() {
        try {
            refreshConnection();
        } catch (PersistanceException ignored) {

        }
    }

    /**
     * Refresh the connection with the data base.
     * @throws PersistanceException when the connection hasn't been established.
     */
    private void refreshConnection() throws PersistanceException {
        try {
            connection = ConnectionDB.getConnection();
            if (connection == null)
                throw new PersistanceException("Error de conexión");
            System.out.println("Conexión activa...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            throw new PersistanceException(ex.getMessage());
        }
    }

    /**
     * Insert a new student into the data base.
     *
     * Use the command: insert into studentsU (institutionalId, name, phone) values ([institutionalId],[name],[phone])
     *
     * @param student to insert.
     * @throws PersistanceException when the connection failed.
     */
    @Override
    public void create(Student student) throws PersistanceException {
        if (connection == null)
            throw new PersistanceException("Error con la conexión");
        try {
            PreparedStatement statement =
                    connection.prepareStatement("insert into studentsU (institutionalId, name, phone) values (?,?,?)");
            statement.setString(1, student.getInstitutionalId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getPhone());
            statement.executeUpdate();
            System.out.println("Statement excecuted: " + "insert into studentsU (institutionalId, name, phone) values ("
                    + student.getInstitutionalId() +", "
                    + student.getName() + ", "
                    + student.getPhone() +")");
        } catch (SQLException ex) {
            throw new PersistanceException(ex.getMessage());
        }


    }

    /**
     * Read the list of students
     *
     * Use the command: select * from studentsU where institutionalId like [key] or name like [key] or phone like [key]
     *
     * @param key coincidence to search.
     * @return {@code List} of student, could be empty if the there's no coincidences found.
     * @throws PersistanceException when the connection failed.
     */
    @Override
    public List<Student> read(String key) throws PersistanceException {
        List<Student> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from studentsU where "
                    + "institutionalId like ? or "
                    + "name like ? or "
                    + "phone like ?"
            );
            statement.setString(1, '%'+key+'%');
            statement.setString(2, '%'+key+'%');
            statement.setString(3, '%'+key+'%');
            resultSet = statement.executeQuery();
            // Read all rows
            while (resultSet.next()) {
                // Create an Student
                Student student = new StudentBuilder()
                        .setId(resultSet.getInt("id"))
                        .setInstitutionalId(resultSet.getString("institutionalId"))
                        .setName(resultSet.getString("name") )
                        .setPhone(resultSet.getString("phone"))
                        .build();
                // Add the student to the list.
                list.add(student);
            }
        } catch (SQLException ex) {
            throw new PersistanceException(ex.getMessage());
        }
        return list;

    }

    /**
     * Reads all the students of the Data Base
     *
     * Use command: select * from studentsU
     * @return {@code List} of student.
     * @throws PersistanceException when the connection failed.
     */
    @Override
    public List<Student> read() throws PersistanceException {
        List<Student> list = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = execute("select * from studentsU");

            // Read all rows
            while (resultSet.next()) {
                // Create an Student
                Student student = new StudentBuilder()
                        .setId(resultSet.getInt("id"))
                        .setInstitutionalId(resultSet.getString("institutionalId"))
                        .setName(resultSet.getString("name") )
                        .setPhone(resultSet.getString("phone"))
                        .build();
                // Add the student to the list.
                list.add(student);
            }
        } catch (SQLException ex) {
            throw new PersistanceException(ex.getMessage());
        }
        return list;
    }

    /**
     * Updates the values of one student using the studentId
     *
     * Use the command: update studentsU set institutionalId=[institutionalId], name=[name], phone=[phone] where id=[studentId]
     *
     * @param student to update values.
     * @throws PersistanceException when the connection failed.
     */
    @Override
    public void update(Student student) throws PersistanceException {
        if (connection == null)
            throw new PersistanceException("Error con la conexión");
        try {
            PreparedStatement statement =
                    connection.prepareStatement("update studentsU set institutionalId=?, name=?, phone=? where id=?");
            statement.setString(1, student.getInstitutionalId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getPhone());
            statement.setInt(4, student.getId());
            statement.executeUpdate();
            System.out.println("Statement excecuted: " + "update studentsU set institutionalId = "
                    + student.getInstitutionalId() +", name= "
                    + student.getName() + ", phone= "
                    + student.getPhone() + "where id="
                    + student.getId() +")");
        } catch (SQLException ex) {
            throw new PersistanceException(ex.getMessage());
        }
    }

    /**
     *
     /**
     * Deletes one student using the studentId
     *
     * Use the command: delete from studentsU where id=[studentId]
     *
     * @param student to update values.
     * @throws PersistanceException when the connection failed.
     */
    @Override
    public void delete(Student student) throws PersistanceException {
        if (connection == null)
            throw new PersistanceException("Error con la conexión");
        try {
            // Prepare statement
            PreparedStatement statement =
                    connection.prepareStatement("delete from studentsU where id=?");
            statement.setInt(1, student.getId());
            // Execution
            statement.executeUpdate();
            // Log
            System.out.println("delete from studentsU where id= "
                    + student.getId() +")");
        } catch (SQLException ex) {
            throw new PersistanceException(ex.getMessage());
        }
    }

    /**
     * Use to know if the connection is established.
     *
     * @return {@code true} connection established, {@code false} error.
     */
    @Override
    public boolean isConnected() {
        return connection!=null;
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

