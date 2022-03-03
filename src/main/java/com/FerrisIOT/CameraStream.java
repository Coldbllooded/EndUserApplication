package com.FerrisIOT;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.swing.*;

public class CameraStream extends JFrame{
    public JPanel camVoid;

    CameraStream(String Address){
        JFrame stream = new JFrame();
        JFXPanel AWCS = new JFXPanel();
        String[] Port = Address.split(":");
        System.out.println(Port[0]);
        System.out.println(Port[1]);
        stream.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Platform.runLater(() -> {
            WebEngine engine;
            WebView wv = new WebView();
            engine = wv.getEngine();
            AWCS.setScene(new Scene(wv));
            engine.load("http://"+ Port[0] + ":" + Port[1] + "/javascript_simple.html");
        });
        stream.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stream.setSize(920,720);
        stream.add(AWCS);
        stream.setVisible(true);
    }
}
