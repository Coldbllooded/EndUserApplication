package com.FerrisIOT;

import javax.swing.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

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
        this.setSize(1000,500);
        this.setVisible(true);

    }
    public void Grid(LinkedList<StationInfo> Dname) {
        for (StationInfo stationInfo : Dname) {
            char[] Stat = stationInfo.getStatus().toCharArray();
            int mem = (int) (((int)Stat[3])-65*4.16);
            AtomicInteger CamSel = new AtomicInteger();
            int WeatherSel = 0;
            int SpeakerSel = 0;
            if((int)Stat[0] > 0)
            {

                //Create Camera Stream Select Grid
                JPanel StreamSelect = new JPanel();
                for (int i = 0; i < (int)Stat[0]; i++) {
                    JButton CB = new JButton();
                    CB.setText(String.valueOf(i));
                    StreamSelect.add(CB);
                    int finalI = i;
                    CB.addActionListener(e -> {
                        CamSel.set(finalI);
                    });
                }
                StreamSelect.revalidate();
            }
            if(Stat[1] > 0)
            {
                //Create Weather Info Select Grid
                JPanel SensorSelect = new JPanel();
                for (int j = 0; j < (int)Stat[1]; j++)
                {
                    JButton WB = new JButton();
                    WB.setText(String.valueOf(j));
                    SensorSelect.add(WB);
                    int finalI = j;
                    WB.addActionListener(e -> {
                        CamSel.set(finalI);
                    });
                }
                SensorSelect.revalidate();
            }
            if(Stat[2] > 0)
            {
                //Create Speaker Select Grid
                JPanel SpeakerSelect = new JPanel();
                for (int k = 0; k < (int)Stat[2]; k++)
                {
                    JButton SB = new JButton();
                    SB.setText(String.valueOf(k));
                    SpeakerSelect.add(SB);
                    int finalI = k;
                    SB.addActionListener(e -> {
                        CamSel.set(finalI);
                    });
                }
                SpeakerSelect.revalidate();
            }
            System.out.println(stationInfo.getName());
            JButton NB = new JButton(stationInfo.getName());
            System.out.println(NB);
            Grid.add(NB);
            NB.addActionListener(e -> {
                new Stream(stationInfo, mem, CamSel.get(),WeatherSel,SpeakerSel);

            });


        }
        Grid.revalidate();
        Grid.setVisible(true);
        choose.setVisible(true);
    }
}
