package com.angelo.wilburspring.models;

import java.util.ArrayList;
import java.util.UUID;

import com.angelo.wilburspring.lessons.Difficulty;

public class Lesson {

    private UUID lessonId;
    private String lessonName;
    private Difficulty lessonDifficulty;
    private ArrayList<Passage> passages = new ArrayList<Passage>();
    
    public UUID getLessonId() {
        return this.lessonId;
    }

    public String lessonName() {
        return this.lessonName;
    }

    public Difficulty getLessonDifficulty() {
        return this.lessonDifficulty;
    }

    public ArrayList<Passage> getPassages() {
        return this.passages;
    }

}