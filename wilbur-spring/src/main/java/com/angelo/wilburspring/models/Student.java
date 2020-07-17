package com.angelo.wilburspring.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.HashMap;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    private UUID studentId;
    private String firstName;
    private String lastName;

    private int performanceValue;
    private HashMap<UUID, Lesson> lessonsTaken = new HashMap<UUID, Lesson>();

    public Student() {
        this.studentId = UUID.randomUUID();
    }

    public Student(String firstName, String lastName) {
        this.studentId = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.performanceValue = 0;
    }

    public Student(UUID studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.performanceValue = 0;
    }

    public UUID getStudentId() {
        return studentId;
    }

    public void setStudentId(UUID studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPerformanceValue() {
        return performanceValue;
    }

    public void setPerformanceValue(int performanceValue) {
        this.performanceValue = performanceValue;
    }

    public HashMap<UUID, Lesson> getLessonsTaken() {
        return lessonsTaken;
    }

    public void setLessonsTaken(HashMap<UUID, Lesson> lessonsTaken) {
        this.lessonsTaken = lessonsTaken;
    }

    
}