package com.angelo.wilburspring.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A lesson is composed of passages which are collections of text that are to be
 * read by the student
 */
@Entity
public class Passage implements Serializable {

    //TODO: Generate better generator
    private static final long serialVersionUID = -7133548841955189058L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name="passage_id", unique=true, nullable=false)
    private UUID passageId;

    @Column(columnDefinition="text", length=10485760)
    public String passageText;

    public Passage(){
        this.passageId = UUID.randomUUID();
    }

    public Passage(String passageText){
        this.passageText = passageText;
    }

    public UUID getPassageId() {
        return this.passageId;
    }

    public String getPassageText() {
        return this.passageText;
    }
}