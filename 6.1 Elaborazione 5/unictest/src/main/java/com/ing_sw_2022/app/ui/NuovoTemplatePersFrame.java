package com.ing_sw_2022.app.ui;

import javax.swing.*;

public class NuovoTemplatePersFrame extends  JFrame{
    private static NuovoTemplatePersFrame nuovoTemplatePersFrame;

    private NuovoTemplatePersFrame(){
        setContentPane(new NuovoTemplatePersPanel().getMainPanel());
        setTitle("Crea template personalizzato");
        setSize(880,500);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocation(UniCTestFrame.getPos()+25,UniCTestFrame.getPos()+35);

    }
    public static NuovoTemplatePersFrame getInstance() {
        if (nuovoTemplatePersFrame == null) nuovoTemplatePersFrame = new NuovoTemplatePersFrame();
        return nuovoTemplatePersFrame;
    }

    public static void destroyInstance() {
        nuovoTemplatePersFrame = null;
    }
}
