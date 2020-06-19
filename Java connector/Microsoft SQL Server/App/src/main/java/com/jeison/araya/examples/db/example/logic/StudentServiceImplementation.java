package com.jeison.araya.examples.db.example.logic;

import com.jeison.araya.examples.db.example.domain.Student;
import com.jeison.araya.examples.db.example.persistance.Persistance;
import com.jeison.araya.examples.db.example.persistance.PersistanceException;
import com.jeison.araya.examples.db.example.persistance.StudentPersistance;

import java.util.List;

public class StudentServiceImplementation implements StudentService<Student, String> {
    // Variables \\
    private static StudentServiceImplementation instance;
    private static Persistance<Student, String> persistance;

    // Singleton \\
    public static StudentServiceImplementation getInstance() {
        if (instance == null)
            instance = new StudentServiceImplementation();
        return instance;
    }

    // Constructor \\
    private StudentServiceImplementation() {
        persistance = new StudentPersistance();

    }

    // Methods \\
    @Override
    public void create(Student student) throws StudentServiceException {
        // Validations
        if (student == null)
        throw new StudentServiceException("No se puede agregar un estudiante nulo.");
        if (student.getInstitutionalId() == null || student.getInstitutionalId().isEmpty())
            throw new StudentServiceException("Carné institucional inválido.");
        if (student.getName() == null || student.getName().isEmpty())
            throw new StudentServiceException("Debe ingresar su nombre.");
        try {
            persistance.create(student);
        } catch (PersistanceException e) {
            throw new StudentServiceException(e.getMessage());
        }
    }

    @Override
    public List<Student> read() throws StudentServiceException {
        try {
            return persistance.read();
        } catch (PersistanceException e) {
            throw new StudentServiceException(e.getMessage());
        }
    }

    @Override
    public List<Student> read(String key) throws StudentServiceException {
        try {
            return persistance.read(key);
        } catch (PersistanceException e) {
            throw new StudentServiceException(e.getMessage());
        }
    }

    @Override
    public void update(Student student) throws StudentServiceException {
        // Validations
        if (student == null)
            throw new StudentServiceException("No se puede actualizar un estudiante nulo.");
        if (student.getInstitutionalId() == null || student.getInstitutionalId().isEmpty())
            throw new StudentServiceException("Carné institucional inválido.");
        if (student.getName() == null || student.getName().isEmpty())
            throw new StudentServiceException("Debe ingresar su nombre.");
        try {
            persistance.update(student);
        } catch (PersistanceException e) {
            throw new StudentServiceException(e.getMessage());
        }
    }

    @Override
    public void delete(Student student) throws StudentServiceException {
        try {
            persistance.delete(student);
        } catch (PersistanceException e) {
            throw new StudentServiceException(e.getMessage());
        }
    }

    @Override
    public boolean isConnected() {
        return persistance.isConnected();
    }
}
