package com.angelo.wilburspring.endpoints;

import java.io.File;
import java.nio.file.Files;

import com.angelo.wilburspring.database.LessonRepository;
import com.angelo.wilburspring.lessons.LessonReader;
import com.angelo.wilburspring.models.Lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonEndpoint {

    @Autowired
    private LessonRepository lessonRepository;

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void readText() {
        System.out.println("HIT IT");
        File fullTextFile = new File("src/main/resources/lessons/AmericanBoysLifeOfTDR.txt");
        String fullText = LessonReader.readTextFile(fullTextFile);
    } 

    //TODO: Update after testing
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value="/add-lesson", method=RequestMethod.POST)
    public Lesson addLesson(@RequestBody Lesson lesson) {
        Lesson newLesson = new Lesson(lesson.getLessonName(), lesson.getLessonDifficulty(), lesson.getPassages());
        lessonRepository.save(newLesson);
        return newLesson;
    }
    
    
    

}