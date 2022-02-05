package com.ing_sw_2022.app;

import java.util.Map;

public interface Lettore {
    Map<String,String> recuperaInfoTestCartaceo(String nomeFile);
    Test correggiTestCartaceo(String cfStudente, String cfTutor, String idTest) throws Exception;
    void confermaCorrezione();
}
