package com.angelo.wilburspring.components;

import com.angelo.wilburspring.database.StudentRepository;
import com.angelo.wilburspring.database.TeacherRepository;
import com.angelo.wilburspring.lessons.Difficulty;
import com.angelo.wilburspring.lessons.TextStructure;
import com.angelo.wilburspring.models.Lesson;
import com.angelo.wilburspring.models.Passage;
import com.angelo.wilburspring.models.Student;
import com.angelo.wilburspring.models.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class TestUserLoader {

	private static final Logger logger=LoggerFactory.getLogger(TestUserLoader.class);
	
	@Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @EventListener(ApplicationReadyEvent.class)
	public void initializeTestUsers() {
        logger.info("Initializing Teacher and Student");
        Student student = new Student();
        student.setFirstName("Test");
        student.setLastName("Student");
        student.setPerformanceValue(50);
        
        Teacher teacher = new Teacher();
        teacher.setFirstName("Test");
        teacher.setLastName("Teacher");
        
        studentRepository.save(student);
        teacherRepository.save(teacher);

	}
    
}