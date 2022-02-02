package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class NuovoTemplateUffFrame extends  JFrame{
    private static NuovoTemplateUffFrame nuovoTemplateUffFrame;

    private NuovoTemplateUffFrame(){
        setContentPane(new NuovoTemplatePersPanel().getMainPanel());
        setTitle("Inserisci template ufficiale");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+25,UniCTestFrame.getPos()+35);

    }
    public static NuovoTemplateUffFrame getInstance() {
        if (nuovoTemplateUffFrame == null) nuovoTemplateUffFrame = new NuovoTemplateUffFrame();
        return nuovoTemplateUffFrame;
    }

    public static void destroyInstance() {
        nuovoTemplateUffFrame = null;
    }
}
