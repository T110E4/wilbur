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
    private String passageText;
    
    @Column(columnDefinition="text", length=10485760)
    private String questionText;

    @Column(columnDefinition="text", length=10485760)
    private String answerAText;
    @Column(columnDefinition="text", length=10485760)
    private String answerBText;
    @Column(columnDefinition="text", length=10485760)
    private String answerCText;
    @Column(columnDefinition="text", length=10485760)
    private String answerDText;
    @Column(columnDefinition="text", length=10485760)
    private String answerEText;

    @Column(name="answer_a_checked")
    private Boolean answerACheckbox;
    @Column(name="answer_b_checked")
    private Boolean answerBCheckbox;
    @Column(name="answer_c_checked")
    private Boolean answerCCheckbox;
    @Column(name="answer_d_checked")
    private Boolean answerDCheckbox;
    @Column(name="answer_e_checked")
    private Boolean answerECheckbox;

    public Passage(){
        this.passageId = UUID.randomUUID();
    }

    public UUID getPassageId() {
        return this.passageId;
    }

    public String getPassageText() {
        return passageText;
    }

    public void setPassageText(String passageText) {
        this.passageText = passageText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerAText() {
        return answerAText;
    }

    public void setAnswerAText(String answerAText) {
        this.answerAText = answerAText;
    }

    public String getAnswerBText() {
        return answerBText;
    }

    public void setAnswerBText(String answerBText) {
        this.answerBText = answerBText;
    }

    public String getAnswerCText() {
        return answerCText;
    }

    public void setAnswerCText(String answerCText) {
        this.answerCText = answerCText;
    }

    public String getAnswerDText() {
        return answerDText;
    }

    public void setAnswerDText(String answerDText) {
        this.answerDText = answerDText;
    }

    public String getAnswerEText() {
        return answerEText;
    }

    public void setAnswerEText(String answerEText) {
        this.answerEText = answerEText;
    }

    public Boolean getAnswerACheckbox() {
        return answerACheckbox;
    }

    public void setAnswerACheckbox(Boolean answerACheckbox) {
        this.answerACheckbox = answerACheckbox;
    }

    public Boolean getAnswerBCheckbox() {
        return answerBCheckbox;
    }

    public void setAnswerBCheckbox(Boolean answerBCheckbox) {
        this.answerBCheckbox = answerBCheckbox;
    }

    public Boolean getAnswerCCheckbox() {
        return answerCCheckbox;
    }

    public void setAnswerCCheckbox(Boolean answerCCheckbox) {
        this.answerCCheckbox = answerCCheckbox;
    }

    public Boolean getAnswerDCheckbox() {
        return answerDCheckbox;
    }

    public void setAnswerDCheckbox(Boolean answerDCheckbox) {
        this.answerDCheckbox = answerDCheckbox;
    }

    public Boolean getAnswerECheckbox() {
        return answerECheckbox;
    }

    public void setAnswerECheckbox(Boolean answerECheckbox) {
        this.answerECheckbox = answerECheckbox;
    }

}