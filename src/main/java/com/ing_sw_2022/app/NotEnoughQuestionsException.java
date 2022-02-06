package com.ing_sw_2022.app;

public class NotEnoughQuestionsException extends Exception{
    //lanciata quando non ci sono abbastanza quesiti per una materia che soddisfino i requisiti del template
    public NotEnoughQuestionsException(String message) { super(message); }
}
