package com.FerrisIOT;

import javax.swing.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Stream extends JFrame {
    private JPanel Box;
    private JFrame InBOX;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        InBOX = new JFrame();
        //Create Camera Stream
        System.out.println(sInfo.getCamStream());
        RTSPStreamContainer x = new RTSPStreamContainer(sInfo.getCamStream()+Cam,1000,100);
        InBOX.add(x);

        //Create Weather Stream
        if (Weat > 0)
            InBOX.add(new Weather(Operations.requestWeather(sInfo.getBasePass(),Main.authenticator.getSessionKey(),sInfo.getUuid())));
        else
            InBOX.add(new NoSensors());

        //Create Speaker Grid
        //Speaker(sInfo./*New Method*/)
        //Create Memory Used Form
        JTextPane Percent = new JTextPane();
        Percent.setText(Memory + "%");
        InBOX.add(Percent);
        InBOX.setVisible(true);
        x.playStream();

    }

}
