package com.ing_sw_2022.app;

public class QuestionNotFoundException extends Exception{
    //lanciata quando l'id del quesito non è corretto
    public QuestionNotFoundException(String message) { super(message); }
}
