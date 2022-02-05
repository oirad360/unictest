package com.ing_sw_2022.app;

import java.io.Serializable;
import java.util.Map;

public interface Lettore extends Serializable {
    static long serialVersionUID = 1;

    Map<String,String> recuperaInfoTestCartaceo(String nomeFile);
    Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws Exception;
    void selezionaRisposta(String idQuesitoReale, String idRisposta);
    Test confermaCorrezione();
}
