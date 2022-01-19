package com.ing_sw_2022.app;

//import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //Caso d'uso di avviamento
        /*UniCTest unictest = UniCTest.getInstance();
        Tutor t = new Tutor("Mario", "Rossi", "CR7");
        unictest.setTutorAutenticato(t);
        Materia m = new Materia("Matematica", "MAT01");
        Materia n = new Materia("Italiano", "ITA02");
        unictest.addMateria(m.getCodice(), m);
        unictest.addMateria(n.getCodice(), n);
        t.addMateriaInsegnata(m);
        t.addMateriaInsegnata(n);
        Visibilità v1 = new Visibilità("Personale", "P1");
        Visibilità v2 = new Visibilità("Privato", "P2");
        Visibilità v3 = new Visibilità("Pubblico", "P3");
        unictest.addVisibilità(v1.getCodice(),v1);
        unictest.addVisibilità(v2.getCodice(),v2);
        unictest.addVisibilità(v3.getCodice(),v3);*/

        //Simulo l'interazione di UC7 che normalmente avverrebbe mediante interfaccia tra Tutor e UniCTest
        /*List<Materia> l = unictest.visualizzaMaterieInsegnate();
        unictest.nuovoQuesito(l.get(0).getCodice());
        unictest.inserisciFonte("MIUR");
        unictest.inserisciTesto("Quanto fa 2+2?");
        unictest.inserisciRisposta("4",true);
        unictest.inserisciRisposta("5", false);
        unictest.inserisciRisposta("6", false);
        unictest.inserisciRisposta("sqrt(16)",true);
        unictest.inserisciDifficoltà(1);
        unictest.confermaQuesito("P1");

        System.out.println("/////////////////////////////////////");
        System.out.println("MATERIA CORRENTE: " + unictest.getMateriaCorrente());
        System.out.println("MAPPA MATERIE:");
        System.out.println(unictest.getMappaMaterie());
        System.out.println("MAPPA VISIBILITà:");
        System.out.println(unictest.getMappaVisibilità());
        System.out.println("MAPPA QUESITI:");
        System.out.println(m.getMappaQuesiti());
        System.out.println("/////////////////////////////////////");*/

    }
}
