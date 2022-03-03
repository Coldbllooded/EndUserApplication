package com.FerrisIOT;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.io.IOException;

public class CameraStream extends JFrame{
    private JPanel camVoid;

    CameraStream(String Address){
        JFrame stream = new JFrame();
        JFXPanel AWCS = new JFXPanel();
        stream.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Platform.runLater(() -> {
            WebEngine engine;
            WebView wv = new WebView();
            engine = wv.getEngine();
            AWCS.setScene(new Scene(wv));
            engine.load("http://"+ Address + ":8080/javascript_simple.html");
        });
        stream.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stream.setSize(640,480);
        stream.add(AWCS);
        stream.setVisible(true);
    }
}
