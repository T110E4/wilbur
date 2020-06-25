package com.angelo.wilburspring.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A lesson is composed of passages which are collections of text that are to be
 * read by the student
 */
@Entity
public class Passage implements Serializable {

    //TODO: Generate better generator
    private static final long serialVersionUID = -7133548841955189058L;
    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int passageId;
    public Integer key;
    public String passageText;

    public Integer getPassageKey() {
        return this.key;
    }

    public String getPassageText() {
        return this.passageText;
    }
}