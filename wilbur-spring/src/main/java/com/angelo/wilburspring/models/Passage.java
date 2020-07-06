package com.angelo.wilburspring.models;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    private String passageText;
    
    @OneToMany(targetEntity = Question.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Question> questions;

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