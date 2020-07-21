package com.angelo.wilburspring.components;

import com.angelo.wilburspring.database.FeedbackRepository;
import com.angelo.wilburspring.lessons.TextStructure;
import com.angelo.wilburspring.models.Feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class FeedbackLoader {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @EventListener(ApplicationReadyEvent.class)
	public void initializePreTest() {

        //Initialize Feedback for Comparison
        Feedback comparison1 = new Feedback();
        comparison1.setFeedbackString(String.join(""
            ,"Think about the subjects of this passage. How are they being compared?"
        ));
        comparison1.setTextStructure(TextStructure.COMPARISON);
        feedbackRepository.save(comparison1);

        Feedback comparison2 = new Feedback();
        comparison2.setFeedbackString(String.join(""
            ," What clue words are there in the paragraph that indicate comparisons?"
        ));
        comparison2.setTextStructure(TextStructure.COMPARISON);
        feedbackRepository.save(comparison2);
    
        //Initialize Feedback for Cause and Effect
        Feedback causeEffect1 = new Feedback();
        causeEffect1.setFeedbackString(String.join(""
            ,"What is the action being taken in the passage?"
        ));
        causeEffect1.setTextStructure(TextStructure.CAUSE_EFFECT);
        feedbackRepository.save(causeEffect1);

        Feedback causeEffect2 = new Feedback();
        causeEffect2.setFeedbackString(String.join(""
            ,"Can you identify the key terms that signify an action in the passage?"
        ));
        causeEffect2.setTextStructure(TextStructure.CAUSE_EFFECT);
        feedbackRepository.save(causeEffect2);

        //Initialize Feedback for Sequence
        Feedback sequence1 = new Feedback();
        sequence1.setFeedbackString(String.join(""
            ,"Can you identify the signal words? Examples: First, next, before, lastly, then"
        ));
        sequence1.setTextStructure(TextStructure.SEQUENCE);
        feedbackRepository.save(sequence1);

        //Initialize Feedback for Sequence
        Feedback sequence2 = new Feedback();
        sequence2.setFeedbackString(String.join(""
            ,"What are some of the signal words that might tell you about the sequence of events?"
        ));
        sequence2.setTextStructure(TextStructure.SEQUENCE);
        feedbackRepository.save(sequence2);

        //Initialize Feedback for Description
        Feedback description1 = new Feedback();
        description1.setFeedbackString(String.join(""
            ,"Can you identify the signal words? Examples: To the left of, above, behind, next to."
        ));
        description1.setTextStructure(TextStructure.DESCRIPTION);
        feedbackRepository.save(description1);

    }
    
}