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
            ," What clue words are there in the paragraph that indicate comparisons?"
        ));
        comparison1.setTextStructure(TextStructure.COMPARISON);
        feedbackRepository.save(comparison1);
        
        //Initialize Feedback for Cause and Effect
        Feedback causeEffect1 = new Feedback();
        causeEffect1.setFeedbackString(String.join(""
            ,"What the action being taken in the passage?"
        ));
        causeEffect1.setTextStructure(TextStructure.CAUSE_EFFECT);
        feedbackRepository.save(causeEffect1);

        //Initialize Feedback for Sequence
        Feedback sequence1 = new Feedback();
        sequence1.setFeedbackString(String.join(""
            ,"Can you identify the signal words? Examples: First, next, before, lastly, then"
        ));
        sequence1.setTextStructure(TextStructure.SEQUENCE);
        feedbackRepository.save(sequence1);

        //Initialize Feedback for Description
        Feedback description1 = new Feedback();
        description1.setFeedbackString(String.join(""
            ,"Can you identify the signal words? Examples: To the left of, above, behind, next to."
        ));
        description1.setTextStructure(TextStructure.DESCRIPTION);
        feedbackRepository.save(description1);

    }
    
}