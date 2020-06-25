package com.angelo.wilburspring.lessons;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
    public static String readTextFile(File incomingFile) {
        String fullText = "";
        try {
            fullText = Files.readString(incomingFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullText;
    }
    
}