package com.angelo.wilburspring.models;

import java.util.UUID;

import com.angelo.wilburspring.lessons.Difficulty;

/**
 * A lesson is composed of passages which are collections of text that are to be
 * read by the student
 */
public class Passage {

    public UUID passageId;
    public String passageText;
    public Difficulty passageDifficulty;

    public UUID getPassageId() {
        return this.passageId;
    }

    public String getPassageText() {
        return this.passageText;
    }
    
    public Difficulty getPassageDifficulty() {
        return this.passageDifficulty;
    }

}