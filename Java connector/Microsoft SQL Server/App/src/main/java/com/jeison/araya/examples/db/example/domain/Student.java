package com.jeison.araya.examples.db.example.domain;

import java.util.Objects;

public class Student {
    // Variables \\
    private int id;
    private String institutionalId;
    private String name;
    private String phone;

    // Constructor \\
    public Student(int id, String institutionalId, String name, String phone) {
        this.id = id;
        this.institutionalId = institutionalId;
        this.name = name;
        this.phone = phone;
    }

    // Methods \\
    public int getId() {
        return id;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }

    public String getInstitutionalId() {
        return institutionalId;
    }

    public Student setInstitutionalId(String institutionalId) {
        this.institutionalId = institutionalId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Student setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", institutionalId='" + institutionalId + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
