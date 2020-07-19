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

import com.angelo.wilburspring.lessons.Difficulty;

@Entity
public class Question implements Serializable {

    private static final long serialVersionUID = -6597532650904471662L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(columnDefinition="text", length=10485760)
    private String questionText;

    private Difficulty difficulty;

    @Column(name="question_id", unique=true, nullable=false)
    private UUID questionId;

    @OneToMany(targetEntity = Answer.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers;

    public Question() {
        this.questionId = UUID.randomUUID();
    }

    public Question(String questionText, Difficulty difficulty) {
        this.questionId = UUID.randomUUID();
        this.questionText = questionText;
        this.difficulty = difficulty;
    }

    public Question(String questionText, Difficulty difficulty, List<Answer> answers) {
        this.questionId = UUID.randomUUID();
        this.questionText = questionText;
        this.difficulty = difficulty;
        this.answers = answers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
}