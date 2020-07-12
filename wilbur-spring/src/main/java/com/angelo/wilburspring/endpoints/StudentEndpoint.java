package com.angelo.wilburspring.endpoints;

import java.util.UUID;

import com.angelo.wilburspring.database.PassageResultRepository;
import com.angelo.wilburspring.database.StudentRepository;
import com.angelo.wilburspring.models.PassageResult;
import com.angelo.wilburspring.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEndpoint {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PassageResultRepository passageResultRepository;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Student createStudent() {
        Student student = new Student(UUID.randomUUID(), "Billy", "Bob");
        return studentRepository.save(student);
    }


    @RequestMapping(value="/add-passage-result", method=RequestMethod.POST)
    public PassageResult savePassageResult(@RequestBody PassageResult passageResult) {
        PassageResult newResult = new PassageResult();
        return passageResultRepository.save(newResult);
    }
    
}