package com.FerrisIOT;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Stream extends JFrame {
    private JPanel Box;
    private JFrame InBOX;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        InBOX = new JFrame();
        JOptionPane NoWeather = new JOptionPane("No Weather Data Available");
        JOptionPane NoSpeaker = new JOptionPane("No Speakers are Available");


        //Create Weather Stream
        if (Weat > 0)
            InBOX.add(new Weather(Operations.requestWeather(sInfo.getBasePass(),Main.authenticator.getSessionKey(),sInfo.getUuid())));
        else
            InBOX.add(NoWeather);

        //Create Speaker Grid
        if(Speak > 0)
            InBOX.add(new Speaker(sInfo.getBasePass(), Speak, sInfo.getUuid()));
        else
            InBOX.add(NoSpeaker);

        //Create Camera Stream
        System.out.println(sInfo.getCamStream());
        RTSPStreamContainer x = new RTSPStreamContainer((sInfo.getCamStream()+"S0"),1000,100);
        InBOX.add(x);

        //Create Memory Used Form
        JTextPane Percent = new JTextPane();
        Percent.setText(Memory + "%");
        Image Icon = new ImageIcon("C:\\Users\\darth\\Desktop\\Senior Projects\\Bulldog.png").getImage();
        this.setIconImage(Icon);
        InBOX.add(Percent);
        InBOX.setVisible(true);
        x.playStream();

    }

}
