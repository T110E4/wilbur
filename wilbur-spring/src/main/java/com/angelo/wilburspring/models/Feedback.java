package com.angelo.wilburspring.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.angelo.wilburspring.lessons.Difficulty;
import com.angelo.wilburspring.lessons.TextStructure;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name="feedback_id", unique=true, nullable=false)
    private UUID feedbackId;
    
    @Column(columnDefinition="text", length=10485760)
    private String feedbackString;

    private TextStructure textStructure;

    private Difficulty difficultyAssociated;

    public Feedback() {
        this.feedbackId = UUID.randomUUID();
    }

    public Feedback(String feedbackString) {
        this.feedbackString = feedbackString;
        this.feedbackId = UUID.randomUUID();
    }

    public UUID getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(UUID feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getFeedbackString() {
        return feedbackString;
    }

    public void setFeedbackString(String feedbackString) {
        this.feedbackString = feedbackString;
    }

    public TextStructure getTextStructure() {
        return textStructure;
    }

    public void setTextStructure(TextStructure textStructure) {
        this.textStructure = textStructure;
    }

    public Difficulty getDifficultyAssociated() {
        return difficultyAssociated;
    }

    public void setDifficultyAssociated(Difficulty difficultyAssociated) {
        this.difficultyAssociated = difficultyAssociated;
    }
    
}