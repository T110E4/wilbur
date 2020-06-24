package com.angelo.wilburspring.endpoints;

import java.io.File;
import java.nio.file.Files;

import com.angelo.wilburspring.lessons.LessonReader;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonEndpoint {


    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public void readText() {
        System.out.println("HIT IT");
        File fullTextFile = new File("src/main/resources/lessons/AmericanBoysLifeOfTDR.txt");
        String fullText = LessonReader.readTextFile(fullTextFile);
    } 
    
    

}