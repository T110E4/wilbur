package com.angelo.wilburspring.lessons;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Class that reads in a lesson from a text file
 */
public class LessonReader {

    /**
     * Given a text file URI, read in the file and return the structured text for storage into a 
     * collection of Passages
     * @param uri
     * @throws IOException
     */
    public void readTextFile(URI uri) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(uri))) {
            stream.forEach(System.out::println);
        }
        
    }
    
}