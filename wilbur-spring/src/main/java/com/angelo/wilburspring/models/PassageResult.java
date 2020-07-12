package com.angelo.wilburspring.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A passage result is the result from a student reading a passage and answer the question
 */
@Entity
public class PassageResult implements Serializable {

    private static final long serialVersionUID = -3987067558616097440L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name="passage_id", unique=true, nullable=false)
    private long passageId;

    @Column(name="passage_result_id", unique=true, nullable=false)
    private UUID passageResultId;

    @Column(name="answer_a_checked")
    private Boolean answerACheckbox;
    @Column(name="answer_b_checked")
    private Boolean answerBCheckbox;
    @Column(name="answer_c_checked")
    private Boolean answerCCheckbox;
    @Column(name="answer_d_checked")
    private Boolean answerDCheckbox;

    //Number correct out of 4
    @Column(name="number_correct")
    private int numberCorrect;

    @Column(columnDefinition="text", length=10485760)
    private String systemResultComments;

    @Column(columnDefinition="text", length=10485760)
    private String studentResultComments;
    
    @Column(name="attempt_count")
    private int attemptCount;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPassageId() {
        return passageId;
    }

    public void setPassageId(long passageId) {
        this.passageId = passageId;
    }

    public UUID getPassageResultId() {
        return passageResultId;
    }

    public void setPassageResultId(UUID passageResultId) {
        this.passageResultId = passageResultId;
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

    public String getSystemResultComments() {
        return systemResultComments;
    }

    public void setSystemResultComments(String systemResultComments) {
        this.systemResultComments = systemResultComments;
    }

    public String getStudentResultComments() {
        return studentResultComments;
    }

    public void setStudentResultComments(String studentResultComments) {
        this.studentResultComments = studentResultComments;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }

}