package com.FerrisIOT;

import javax.swing.*;

public class Weather extends JFrame{
    private JPanel Info;
    Weather(String WIN)
    {
        JPanel Weather = new JPanel();
        Weather.setSize(100,100);
        String[] WINS;
        WINS = WIN.split("\\|");
        String Press = WINS[0];
        String Temp = WINS[1];
        JTextPane TInfo = new JTextPane();
        TInfo.setText(Temp);
        JTextPane PInfo = new JTextPane();
        PInfo.setText(Press);
        Weather.add(TInfo);
        Weather.add(PInfo);
        Weather.setVisible(true);

    }
}
