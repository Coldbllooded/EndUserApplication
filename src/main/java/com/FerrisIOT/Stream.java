package com.FerrisIOT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static java.awt.Font.BOLD;
import static org.apache.commons.lang3.StringUtils.chop;

public class Stream extends JFrame {
    private JPanel Box;
    private JPanel Camera;
    private JPanel WeatherInfo;
    private JPanel SpeakerButton;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        //Create Camera Stream
        if(Cam > 0)
        {
            System.out.println(sInfo.getCamStream());
            RTSPStreamContainer x = new RTSPStreamContainer((sInfo.getCamStream() + Cam), 1000, 100);
            Camera.add(x);
            x.playStream();
        }
        else
        {
            JLabel NOCAMS = new JLabel("No Camera Streams are Available", SwingConstants.CENTER);
            Font Type1 = new Font("Bernard MT Condensed", BOLD, 40);
            NOCAMS.setForeground(Color.WHITE);
            NOCAMS.setFont(Type1);
            Camera.setSize(1000,1000);
            Camera.add(NOCAMS);
        }
        //Create Speaker Button
        if(Speak > 0)
        {
            JButton PLAY = new JButton("Play Sound");
            PLAY.addActionListener(e-> {
                try {
                    Operations.playSpeaker(sInfo.getBasePass(),sInfo.getUuid());
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (KeyManagementException ex) {
                    ex.printStackTrace();
                }
            });
            SpeakerButton.add(PLAY);

        }
        else
        {
            JLabel NOSPEAKER = new JLabel("No Speakers are Available");
            NOSPEAKER.setForeground(Color.BLACK);
            SpeakerButton.add(NOSPEAKER);
        }
        //Create Weather Info Section
        if (Weat > 0) {
            String WIN = Operations.requestWeather(sInfo.getBasePass(), Main.authenticator.getSessionKey(), sInfo.getUuid(), Main.authenticator.getUserID());
            String[] WINS = WIN.split("\\|");
            String Press = WINS[0];
            String Temp = WINS[1];
            String Humid = WINS[2];
            Humid = chop(Humid);
            Humid = chop(Humid);
            JLabel Pressure = new JLabel("Pressure: " + Press);
            Pressure.setForeground(Color.BLACK);
            JLabel Temperature = new JLabel("Temperature: " + Temp + "F");
            Temperature.setForeground(Color.BLACK);
            JLabel Humidity = new JLabel("Humidity: " + Humid + "%");
            Humidity.setForeground(Color.BLACK);
            WeatherInfo.add(Pressure);
            WeatherInfo.add(Temperature);
            WeatherInfo.add(Humidity);
        }
        else {
            JLabel NOWEATHER = new JLabel("No Weather Info is Available");
            NOWEATHER.setForeground(Color.BLACK);
            WeatherInfo.add(NOWEATHER);
        }
        //Main Container Set Visible and Max size
        this.setSize(1000,1030);
        this.setContentPane(Box);
    }

}
