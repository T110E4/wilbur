package com.angelo.wilburspring.components;

import com.angelo.wilburspring.database.LessonRepository;
import com.angelo.wilburspring.lessons.Difficulty;
import com.angelo.wilburspring.models.Lesson;
import com.angelo.wilburspring.models.Passage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LessonLoader {

	private static final Logger logger=LoggerFactory.getLogger(LessonLoader.class);
	
	@Autowired
    private LessonRepository lessonRepository;

    @EventListener(ApplicationReadyEvent.class)
	public void initializePreTest() {
        logger.info("Initializing Pre-Test Evaluation");
		Lesson preTest = new Lesson("Pre-test Evaluation Quiz", Difficulty.EASY);
		String preTestDescription = String.join(""
			, "This test is designed to help evlauate your current performance with reading comprehension." 
			, " You will be presented with a number of passages, and after each passage you will be asked one question about the passage."
			, " You will choose an answer (A), (B), (C), (D) and or (E) from the answer options."
		);
		preTest.setLessonSummary(preTestDescription);

		//Passage 1
		Passage passage1 = new Passage();
		String passageText = String.join(""
		,"The cat jumped onto the gate and stretched out. Moments later, Gerald the dog walked out"
		," of the big blue house. When it saw him walk past the cat lunged into the air and bolted under the porch."
		);
		passage1.setPassageText(passageText);
		passage1.setQuestionText("In this passage, what does the word 'it' refer to?");
		passage1.setAnswerAText("The Cat");
		passage1.setAnswerBText("The Dog");
		passage1.setAnswerCText("The big blue house");
		passage1.setAnswerDText("Gerald");
		passage1.setAnswerEText("Stretching");
		passage1.setAnswerACheckbox(true);
		preTest.addPassage(passage1);

		//Passage 2
		Passage passage2= new Passage();
		passageText = String.join(""
		,"Billy the goat enjoys eating grass. When Billy isn't eating grass, he is singing to his best friend Ralph."
		," His favorite song is 'bah bah blacksheep'. He sings it every night before bed."
		," Billy's best friend is a squirrel."
		);
		passage2.setPassageText(passageText);
		passage2.setQuestionText("What is the squirrel's name?");
		passage2.setAnswerAText("The Cat");
		passage2.setAnswerBText("Billy");
		passage2.setAnswerCText("Ralph");
		passage2.setAnswerDText("Gerald");
		passage1.setAnswerEText("The goat");
		passage2.setAnswerCCheckbox(true);
		preTest.addPassage(passage2);

		//Passage 3
		Passage passage3= new Passage();
		passageText = String.join(""
		,"When Billy the goat is happy, he jumps up and down."
		," He smiles with delight at the sight of hay."
		);
		passage3.setPassageText(passageText);
		passage3.setQuestionText("In this passage, what makes Billy happy?");
		passage3.setAnswerAText("The Cat");
		passage3.setAnswerBText("Hay");
		passage3.setAnswerCText("His best friend");
		passage3.setAnswerDText("Jumping");
		passage1.setAnswerEText("Running");
		passage3.setAnswerBCheckbox(true);
		preTest.addPassage(passage3);

		lessonRepository.save(preTest);

	}
    
}