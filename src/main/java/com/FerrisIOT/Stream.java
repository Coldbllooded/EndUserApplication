package com.FerrisIOT;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class Stream extends JFrame {
    private JPanel Box;
    private JPanel Camera;
    private JPanel WeathInfo;
    private JPanel SpeakerButton;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        JOptionPane NoWeather = new JOptionPane("No WeatherPane Data Available");
        JOptionPane NoSpeaker = new JOptionPane("No Speakers are Available");
        JOptionPane NoCameras = new JOptionPane("No Cameras Streams are Available");
        this.setContentPane(Box);

        //Create WeatherPane Stream
        if (Weat > 0) {
            WeatherPanel PIP = new WeatherPanel();
            System.out.println("Created new WeatherPane");
            System.out.println("Added PIP to WEATHPANE");
            PIP.setWeatherData(Objects.requireNonNull(Operations.requestWeather(sInfo.getBasePass(), Main.authenticator.getSessionKey(), sInfo.getUuid(), Main.authenticator.getUserID())));
            System.out.println("Got server's last weather reading");
            ActionListener listener = e -> {
                try {
                    PIP.setWeatherData(Objects.requireNonNull(Operations.requestWeather(sInfo.getBasePass(), Main.authenticator.getSessionKey(), sInfo.getUuid(), Main.authenticator.getUserID())));
                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    ex.printStackTrace();
                }
            };
            Timer timer = new Timer(7000, listener);
            System.out.println("Setup new timer");
            timer.start();
            WeathInfo.setVisible(true);
            System.out.println("Set WeathInfo visible");

            WeathInfo.add(PIP);


        }

        else

/*
        //Create Speaker Grid
        if(Speak > 0)
            Speaker.add(new Speaker(sInfo.getBasePass(), Speak, sInfo.getUuid()));
        else
            Speaker.add(NoSpeaker);

        //Create Camera Stream
        if(Cam > 0) {
            System.out.println(sInfo.getCamStream());
            RTSPStreamContainer x = new RTSPStreamContainer((sInfo.getCamStream() + Cam), 1000, 100);
            Camera.add(x);
            x.playStream();
        }
        else
        {
            Camera.add(NoCameras);
        }

         */

        //Create Memory Used Form
        //Box.add(Camera);
        //Box.add(Speaker);
        //Box.add(Weather);
        Box.setVisible(true);
    }

}
