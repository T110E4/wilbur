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

    //ID of the associated passage
    private long passageId;

    @Column(columnDefinition="text", length=10485760)
    private String answerText = "";

    @Column(name="answer_a_checked")
    private Boolean answerA;
    @Column(name="answer_a_correct")
    private Boolean answerACorrect;
    @Column(name="answer_b_checked")
    private Boolean answerB;
    @Column(name="answer_b_correct")
    private Boolean answerBCorrect;
    @Column(name="answer_c_checked")
    private Boolean answerC;
    @Column(name="answer_c_correct")
    private Boolean answerCCorrect;
    @Column(name="answer_d_checked")
    private Boolean answerD;
    @Column(name="answer_d_correct")
    private Boolean answerDCorrect;
    @Column(name="answer_e_checked")
    private Boolean answerE;
    @Column(name="answer_e_correct")
    private Boolean answerECorrect;

    private String[] studentAnswers;
    
    //Percentage correct for the given passage
    private Boolean correct = true;

    public Answer(){
        this.answerACorrect = false;
        this.answerBCorrect = false;
        this.answerCCorrect = false;
        this.answerDCorrect = false;
        this.answerECorrect = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public long getPassageId() {
        return passageId;
    }

    public void setPassageId(long passageId) {
        this.passageId = passageId;
    }

    public Boolean getAnswerACorrect() {
        return answerACorrect;
    }

    public void setAnswerACorrect(Boolean answerACorrect) {
        this.answerACorrect = answerACorrect;
    }

    public Boolean getAnswerBCorrect() {
        return answerBCorrect;
    }

    public void setAnswerBCorrect(Boolean answerBCorrect) {
        this.answerBCorrect = answerBCorrect;
    }

    public Boolean getAnswerCCorrect() {
        return answerCCorrect;
    }

    public void setAnswerCCorrect(Boolean answerCCorrect) {
        this.answerCCorrect = answerCCorrect;
    }

    public Boolean getAnswerDCorrect() {
        return answerDCorrect;
    }

    public void setAnswerDCorrect(Boolean answerDCorrect) {
        this.answerDCorrect = answerDCorrect;
    }

    public Boolean getAnswerECorrect() {
        return answerECorrect;
    }

    public void setAnswerECorrect(Boolean answerECorrect) {
        this.answerECorrect = answerECorrect;
    }

    public Boolean getAnswerA() {
        return answerA;
    }

    public void setAnswerA(Boolean answerA) {
        this.answerA = answerA;
    }

    public Boolean getAnswerB() {
        return answerB;
    }

    public void setAnswerB(Boolean answerB) {
        this.answerB = answerB;
    }

    public Boolean getAnswerC() {
        return answerC;
    }

    public void setAnswerC(Boolean answerC) {
        this.answerC = answerC;
    }

    public Boolean getAnswerD() {
        return answerD;
    }

    public void setAnswerD(Boolean answerD) {
        this.answerD = answerD;
    }

    public Boolean getAnswerE() {
        return answerE;
    }

    public void setAnswerE(Boolean answerE) {
        this.answerE = answerE;
    }

    public String[] getStudentAnswers() {
        return studentAnswers;
    }

    public void setStudentAnswers(String[] studentAnswers) {
        this.studentAnswers = studentAnswers;
    }
    
}