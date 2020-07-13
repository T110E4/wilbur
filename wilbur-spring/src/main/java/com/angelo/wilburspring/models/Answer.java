package com.angelo.wilburspring.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Answer implements Serializable {

    private static final long serialVersionUID = 5002500685140216964L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    private long passageId;
    private long lessonId;

    @Column(columnDefinition="text", length=10485760)
    private String answerText = "";
    
    //Percentage correct for the given passage
    private Double correct = 0.0;

    public Answer(){
        
    }

    public Answer(String answerText, Double correct) {
        this.answerText = answerText;
        this.correct = correct;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Double getCorrect() {
        return correct;
    }

    public void setCorrect(Double correct) {
        this.correct = correct;
    }

    public long getPassageId() {
        return passageId;
    }

    public void setPassageId(long passageId) {
        this.passageId = passageId;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }
    
}