package com.FerrisIOT;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CameraGrid extends JFrame {
    CameraGrid(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<WebcamPanel> panels = new ArrayList<>();
        Webcam webcam = Webcam.getDefault();
        int Num = Webcam.getWebcams().size();
        this.setLayout(new GridLayout(0, Num, 1, 1));
        //Get the number of seen devices
        if(webcam == null)
        {
            NoCamerasConnected NO = new NoCamerasConnected();
        }
        else
        {
            for(Webcam web: Webcam.getWebcams())
            {
                WebcamPanel panel = new WebcamPanel(web, new Dimension(600, 600), false);
                panel.setDrawMode(WebcamPanel.DrawMode.FIT);
                panel.setFPSLimited(true);
                panel.setFPSLimit(32); // 0.2 FPS = 1 frame per 5 seconds
                panel.setBorder(BorderFactory.createEmptyBorder());
                this.add(panel);
                panels.add(panel);
            }
            this.pack();
            this.setVisible(true);
            for(WebcamPanel panel : panels)
            {
                panel.start();
            }
        }
    }
}
