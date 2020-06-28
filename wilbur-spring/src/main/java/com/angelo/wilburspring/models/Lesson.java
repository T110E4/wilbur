package com.angelo.wilburspring.models;

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
public class Lesson {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    private long id;

    @Column(name="lesson_id", unique=true, nullable=false)
    private UUID lessonId;

    private String lessonName;
    
    @Column(columnDefinition="text", length=10485760)
    private String lessonSummary;

    private Difficulty lessonDifficulty;

    /**
     * The passages associated with this lesson
     */
    @OneToMany(targetEntity = Passage.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Passage> passages;

    private Lesson() {
        this.lessonId = UUID.randomUUID();
    }

    public Lesson(String lessonName, Difficulty lessonDifficulty) {
        this.lessonId = UUID.randomUUID();
        this.lessonName = lessonName;
        this.lessonDifficulty = lessonDifficulty;
    }

    public Lesson(String lessonName, String lessonSummary, Difficulty lessonDifficulty, List<Passage> passages) {
        this.lessonId = UUID.randomUUID();
        this.lessonName = lessonName;
        this.lessonSummary = lessonSummary;
        this.lessonDifficulty = lessonDifficulty;
        this.passages = passages;
    }

    public long getId() {
        return this.id;
    }

    public UUID getLessonId() {
        return this.lessonId;
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