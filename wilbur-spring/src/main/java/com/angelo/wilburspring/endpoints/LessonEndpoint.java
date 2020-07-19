package com.angelo.wilburspring.endpoints;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

    /**
     * Save the answer and respond with the Answer object with filled in correctness
     * @param answer
     * @return
     */
    @RequestMapping(value = "/save-answer", method = RequestMethod.POST)
    public Answer saveAnswer(
            @RequestBody Answer answer) {
        Passage passage = passageRepository.findById(answer.getPassageId()).orElse(null);
        /**
         * We compensate for React limitations by taking the array of
         * answers selected by the student and setting the boolean appropriately
         * for each option
         */
        for (String studentAnswerString : answer.getStudentAnswers() ) {
            if (studentAnswerString.equals("answerA")) {
                answer.setAnswerA(true);
            } else if (studentAnswerString.equals("answerB")) {
                answer.setAnswerB(true);
            } else if (studentAnswerString.equals("answerC")) {
                answer.setAnswerC(true);
            } else if (studentAnswerString.equals("answerD")) {
                answer.setAnswerD(true);
            } else if (studentAnswerString.equals("answerE")) {
                answer.setAnswerE(true);
            }
        }
        //TODO: Add support for non-multiple choice
        Double multiChoiceCorrect = 0.0;
        if (passage.getAnswerACheckbox() == answer.getAnswerA()) {
            multiChoiceCorrect += 1;
            answer.setAnswerACorrect(true);
        } else {

        }
        if (passage.getAnswerBCheckbox() == answer.getAnswerB()) {
            multiChoiceCorrect += 1;
            answer.setAnswerBCorrect(true);
        } else {
            answer.setCorrect(false);
        }
        if (passage.getAnswerCCheckbox() == answer.getAnswerC()) {
            multiChoiceCorrect += 1;
            answer.setAnswerCCorrect(true);
        }
        if (passage.getAnswerDCheckbox() == answer.getAnswerD()) {
            multiChoiceCorrect += 1;
            answer.setAnswerDCorrect(true);
        }
        if (passage.getAnswerECheckbox() == answer.getAnswerE()) {
            multiChoiceCorrect += 1;
            answer.setAnswerECorrect(true);
        }
        //If any answers are incorrect, mark the answer incorrect.
        if (!answer.getAnswerACorrect() || !answer.getAnswerBCorrect() || !answer.getAnswerCCorrect()
            || !answer.getAnswerDCorrect() || !answer.getAnswerECorrect() ) {
                answer.setCorrect(false);
            }
        answerRepository.save(answer);
        return answer;
    }

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
        Long longId = Long.valueOf(id);
        List<Feedback> feedbackList = null;
        Feedback chosenFeedback = null;
        Passage passage = passageRepository.findById(longId).orElse(null);
        TextStructure passageType = passage.getTextStructure();

        feedbackList = this.getFeedbackContent(passageType);
        //TOOO: Update to choose based on difficulty
        Random rand = new Random();
        chosenFeedback = feedbackList.get(rand.nextInt(feedbackList.size()));
        return chosenFeedback;
    }
    
}