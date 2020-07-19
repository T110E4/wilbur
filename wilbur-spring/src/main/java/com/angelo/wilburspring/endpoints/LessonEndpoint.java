package com.angelo.wilburspring.endpoints;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.websocket.Decoder.TextStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.angelo.wilburspring.database.AnswerRepository;
import com.angelo.wilburspring.database.LessonRepository;
import com.angelo.wilburspring.database.PassageRepository;
import com.angelo.wilburspring.lessons.TextStructure;
import com.angelo.wilburspring.models.Answer;
import com.angelo.wilburspring.models.Feedback;
import com.angelo.wilburspring.models.Lesson;
import com.angelo.wilburspring.models.Passage;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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

    @Autowired
    private AnswerRepository answerRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    private static final Logger logger=LoggerFactory.getLogger(LessonEndpoint.class);

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

    @RequestMapping(value = "/save-answer", method = RequestMethod.POST)
    public Answer saveAnswer(@RequestBody Answer answer) {
        Answer newAnswer = new Answer();
        answerRepository.save(newAnswer);
        return newAnswer;
    }

    //TODO: passageRepository not finding IDs that exist
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

    public List<Feedback> getFeedbackContent(TextStructure feedbackType){
        Session session = entityManager.unwrap(Session.class);

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Feedback> criteria = builder.createQuery(Feedback.class);
        //criteria.from(Feedback.class);
        Root<Feedback> root = criteria.from(Feedback.class);
        criteria.select(root).where(builder.equal(root.get("textStructure"), feedbackType.ordinal() ));
        Query<Feedback> query = session.createQuery(criteria);
        List<Feedback> feedbackList = query.getResultList();
        logger.info(String.format("Found %d feedback options", feedbackList.size()));
        return feedbackList; 
    }

    @RequestMapping(value = "/get-feedback", method = RequestMethod.GET)
    public Feedback getFeedback(@RequestParam String id) {
        List<Feedback> feedbackList = null;
        Feedback chosenFeedback = null;

        UUID passageId = UUID.fromString(id);
        Session session = entityManager.unwrap(Session.class);
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Passage> criteria = builder.createQuery(Passage.class);
        Root<Passage> root = criteria.from(Passage.class);
        criteria.select(root).where(builder.equal(root.get("passageId"), passageId));
        Query<Passage> query = session.createQuery(criteria);
        List<Passage> passageList = query.getResultList();
        logger.info(Integer.toString(passageList.size()));
        if (passageList.size() > 0) {
            Passage passage = passageList.get(0);
            TextStructure passageType = passage.getTextStructure();
            feedbackList = this.getFeedbackContent(passageType);
        
            //TOOO: Update to choose based on difficulty
            Random rand = new Random();
            chosenFeedback = feedbackList.get(rand.nextInt(feedbackList.size()));
        } else {
            return null;
        }
        return chosenFeedback;
    }
    
}