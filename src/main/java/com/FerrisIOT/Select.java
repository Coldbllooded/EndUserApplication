package com.FerrisIOT;

import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Select extends JFrame {
    private JPanel choose;
    public  JPanel Grid;
    private JButton Close;

    Select(LinkedList<StationInfo> stationInfos){
        Grid(stationInfos);
        Close.addActionListener(e-> {
            this.dispose();
        });
        this.setContentPane(choose);
        Image Icon = new ImageIcon("C:\\Users\\darth\\Desktop\\Senior Projects\\Bulldog.png").getImage();
        this.setIconImage(Icon);
        this.setSize(1000,500);
        this.setVisible(true);

    }
    public void Grid(LinkedList<StationInfo> Dname) {
        for (StationInfo stationInfo : Dname) {
            char[] Stat = stationInfo.getStatus().toCharArray();
            int mem = (int) (((int) Stat[3]) - 65 * 4.16);
            int CamSel = Integer.parseInt(String.valueOf(Stat[0]));
            int WeatherSel = Integer.parseInt(String.valueOf(Stat[1]));
            int SpeakerSel = Integer.parseInt(String.valueOf(Stat[2]));
            System.out.println(Stat);
            JButton NB = new JButton(stationInfo.getName());
            Grid.add(NB);
            NB.addActionListener(e -> {
                try {
                    Stream G = new Stream(stationInfo, mem, CamSel, WeatherSel, SpeakerSel);
                    G.setSize(800,1000);
                    G.setVisible(true);
                    G.STREAMPLAY();

                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    ex.printStackTrace();
                } catch (MqttException ex) {
                    ex.printStackTrace();
                }

            });


        }

        Grid.revalidate();
        Grid.setVisible(true);
        choose.setVisible(true);
    }
}
