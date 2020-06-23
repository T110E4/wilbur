package com.angelo.wilburspring.endpoints;

import java.util.UUID;

import com.angelo.wilburspring.database.StudentRepository;
import com.angelo.wilburspring.models.Student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentEndpoint {

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Student createStudent() {
        System.out.println("HIT IT");
        Student student = new Student(UUID.randomUUID(), "Billy", "Bob");
        return studentRepository.save(student);
    } 
    
}