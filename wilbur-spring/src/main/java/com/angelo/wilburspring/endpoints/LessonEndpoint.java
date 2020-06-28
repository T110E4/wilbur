package com.angelo.wilburspring.endpoints;

import java.util.List;

import com.angelo.wilburspring.database.LessonRepository;
import com.angelo.wilburspring.database.PassageRepository;
import com.angelo.wilburspring.models.Lesson;
import com.angelo.wilburspring.models.Passage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonEndpoint {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private PassageRepository passageRepository;

    @RequestMapping(value="/add-lesson", method=RequestMethod.POST)
    public Lesson addLesson(@RequestBody Lesson lesson) {
        Lesson newLesson = new Lesson(lesson.getLessonName(), lesson.getLessonSummary(), lesson.getLessonDifficulty(), lesson.getPassages());
        lessonRepository.save(newLesson);
        return newLesson;
    }

    @RequestMapping(value = "/get-lessons", method = RequestMethod.GET)
    public List<Lesson> getLessons() {
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons;
    }

    
    @RequestMapping(value = "/get-lesson", method = RequestMethod.GET)
    public Lesson getLessons(@RequestParam Long id) {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        return lesson;
    }

    //TODO: passageRepository not fiding IDs that exist
    @RequestMapping(value = "/get-passage", method = RequestMethod.GET, produces = "application/json")
    public Passage getPassage(@RequestParam String id) {
        Long longId = Long.valueOf(id);
        Passage passage = passageRepository.findById(longId).orElse(null);
        return passage;
    }

    /**
     * Get passages for a specific lesson, given by the lesson ID
     * @param id
     * @return List<Passage> passageList
     */
    @RequestMapping(value = "/get-passages", method = RequestMethod.GET)
    public List<Passage> getPassages(@RequestParam String id) {
        Long longId = Long.valueOf(id);
        Lesson lesson = lessonRepository.findById(longId).orElse(null);
        if (lesson != null) { 
            return lesson.getPassages();
        } else {
            return null;
        }
    }
    
}