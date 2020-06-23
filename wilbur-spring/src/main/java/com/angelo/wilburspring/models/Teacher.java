package com.angelo.wilburspring.models;

import java.util.UUID;

public class Teacher {

    private final UUID teacherId;
    private final String firstName;
    private final String lastName;

    public Teacher(UUID teacherId, String firstName, String lastName) {
        this.teacherId = teacherId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UUID getId() {
        return this.teacherId;
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
}