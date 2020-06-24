package com.angelo.wilburspring.endpoints;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LessonCreatorEndpoint {
    
    @RequestMapping(value = "/generate-lesson", method = RequestMethod.GET)
    public void createLesson() {
        
    } 

}