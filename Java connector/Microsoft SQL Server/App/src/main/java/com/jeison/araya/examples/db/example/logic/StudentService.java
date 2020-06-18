package com.jeison.araya.examples.db.example.logic;

import com.jeison.araya.examples.db.example.domain.Student;

import java.util.List;

public interface StudentService<S, K> {
    void create (S student) throws StudentServiceException;
    List<S> read () throws StudentServiceException;
    List<S> read(K k) throws StudentServiceException;
    void update (S student) throws StudentServiceException;
    void delete (S student) throws StudentServiceException;
}
