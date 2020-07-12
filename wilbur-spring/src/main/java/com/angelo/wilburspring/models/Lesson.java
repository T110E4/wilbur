package com.angelo.wilburspring.models;

import java.util.ArrayList;
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
        this.passages = new ArrayList<Passage>();
    }

    public Lesson(String lessonName, Difficulty lessonDifficulty) {
        this.lessonId = UUID.randomUUID();
        this.passages = new ArrayList<Passage>();
        this.lessonName = lessonName;
        this.lessonDifficulty = lessonDifficulty;
    }

    public Lesson(String lessonName, String lessonSummary, Difficulty lessonDifficulty, List<Passage> passages) {
        this.lessonId = UUID.randomUUID();
        this.passages = new ArrayList<Passage>();
        this.lessonName = lessonName;
        this.lessonSummary = lessonSummary;
        this.lessonDifficulty = lessonDifficulty;
        this.passages = passages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getLessonId() {
        return lessonId;
    }

    public void setLessonId(UUID lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getLessonSummary() {
        return lessonSummary;
    }

    public void setLessonSummary(String lessonSummary) {
        this.lessonSummary = lessonSummary;
    }

    public Difficulty getLessonDifficulty() {
        return lessonDifficulty;
    }

    public void setLessonDifficulty(Difficulty lessonDifficulty) {
        this.lessonDifficulty = lessonDifficulty;
    }

    public List<Passage> getPassages() {
        return passages;
    }

    public void setPassages(List<Passage> passages) {
        this.passages = passages;
    }

    public void addPassage(Passage passageToAdd){
        this.passages.add(passageToAdd);
    }


}