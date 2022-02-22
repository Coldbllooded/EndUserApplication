package com.FerrisIOT;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;

import javax.swing.*;

public class CameraStream {
    public static void main(){
        IpCamDeviceRegistry.register("CameraName",Main.ActiveStream, IpCamMode.PULL);
        WebcamPanel panel = new WebcamPanel(Webcam.getWebcams().get(0));
        panel.setFPSLimit(7.5);
        JFrame Cam = new JFrame("Live Camera View");
        Cam.add(panel);
        Cam.pack();
        Cam.setVisible(true);
        Cam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
