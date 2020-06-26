package com.angelo.wilburspring.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.angelo.wilburspring.lessons.Difficulty;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Lesson {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int lessonId;

    private String lessonName;
    
    @Column(columnDefinition="text", length=10485760)
    private String lessonSummary;

    private Difficulty lessonDifficulty;

    @OneToMany(targetEntity = Passage.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Passage> passages;

    private Lesson() {}

    public Lesson(String lessonName, Difficulty lessonDifficulty) {
        this.lessonName = lessonName;
        this.lessonDifficulty = lessonDifficulty;
    }

    public Lesson(String lessonName, String lessonSummary, Difficulty lessonDifficulty, List<Passage> passages) {
        this.lessonName = lessonName;
        this.lessonSummary = lessonSummary;
        this.lessonDifficulty = lessonDifficulty;
        this.passages = passages;
    }

    public String getLessonName() {
        return this.lessonName;
    }

    public String getLessonSummary() {
        return this.lessonSummary;
    }

    public Difficulty getLessonDifficulty() {
        return this.lessonDifficulty;
    }

    public List<Passage> getPassages() {
        return this.passages;
    }

}