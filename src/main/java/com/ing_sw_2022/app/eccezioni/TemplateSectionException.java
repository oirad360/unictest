package com.ing_sw_2022.app.eccezioni;

public class TemplateSectionException extends Exception{
    //lanciata quando il numero di quesiti inseriti non è congruente al numero di quesiti richiesti dalla sezione del template scelto
    public TemplateSectionException(String message) { super(message); }
}
