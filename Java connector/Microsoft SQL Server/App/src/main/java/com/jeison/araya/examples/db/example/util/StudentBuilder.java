package com.jeison.araya.examples.db.example.util;

import com.jeison.araya.examples.db.example.domain.Student;

public class StudentBuilder {
    // Variables \\
    private int id;
    private String institutionalId;
    private String name;
    private String phone;

    public StudentBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public StudentBuilder setInstitutionalId(String institutionalId) {
        this.institutionalId = institutionalId;
        return this;
    }

    public StudentBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StudentBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Student build(){
        return new Student(id, institutionalId, name, phone);
    }
}
