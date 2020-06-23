package com.angelo.wilburspring.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import java.util.HashMap;

@Entity
public class Student {

    private @Id @GeneratedValue Long id;

    private UUID studentId;
    private String firstName;
    private String lastName;

    private int performanceValue;
    private HashMap<UUID, Lesson> lessonsTaken = new HashMap<UUID, Lesson>();

    public Student() {}

    public Student(UUID studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.performanceValue = 0;
    }

    public UUID getId() {
        return this.studentId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        return String.format("%s %s", this.firstName, this.lastName);
    }

    public int getPerformanceValue() {
        return this.performanceValue;
    }

    public HashMap<UUID, Lesson> getLessons() {
        return this.lessonsTaken;
    }
    
}