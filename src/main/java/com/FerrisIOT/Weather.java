package com.FerrisIOT;

import javax.swing.*;

import static org.apache.commons.lang3.StringUtils.chop;

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
        String Humidity = WINS[2];
        Humidity = chop(Humidity);
        Humidity = chop(Humidity);
        JTextPane TInfo = new JTextPane();
        TInfo.setText(Temp + "F");
        JTextPane PInfo = new JTextPane();
        PInfo.setText(Press);
        JTextPane HInfo = new JTextPane();
        HInfo.setText(Humidity + "%");
        Weather.add(TInfo);
        Weather.add(PInfo);
        Weather.add(HInfo);
        Weather.setVisible(true);

    }
}
