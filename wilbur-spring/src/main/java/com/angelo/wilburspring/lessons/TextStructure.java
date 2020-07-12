package com.angelo.wilburspring.lessons;

public enum TextStructure {
    COMPARISON("Comparison"),
    CAUSE_EFFECT("Cause and Effect"), 
    SEQUENCE("Sequence"), 
    DESCRIPTION("Description");

    private String value;
    public String getValue() {
        return value;
    }
    private TextStructure(String value){
        this.value=value;
    }
}