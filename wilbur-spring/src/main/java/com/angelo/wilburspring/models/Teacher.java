package com.angelo.wilburspring.models;

import java.util.HashMap;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    private UUID teacherId;
    private String firstName;
    private String lastName;
    private HashMap<UUID, Student> assignedStudents;

    public Teacher(){
        this.teacherId = UUID.randomUUID();
    }

    public Teacher(UUID teacherId, String firstName, String lastName) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(UUID teacherId) {
        this.teacherId = teacherId;
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

    public HashMap<UUID, Student> getAssignedStudents() {
        return assignedStudents;
    }

    public void setAssignedStudents(HashMap<UUID, Student> assignedStudents) {
        this.assignedStudents = assignedStudents;
    }

}