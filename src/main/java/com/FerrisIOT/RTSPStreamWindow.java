package com.FerrisIOT;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;

public class RTSPStreamWindow extends JFrame {

    /*
    REQUIRES following maven dependency:

        <dependency>
            <groupId>uk.co.caprica</groupId>
            <artifactId>vlcj</artifactId>
            <version>4.7.1</version>
        </dependency>

     VLCJ is entirely standalone and works wherever needed. the host operating system can optionally have VLC not installed for this to work properly
     */

    EmbeddedMediaPlayerComponent mediaPlayerComponent;
    String address;
    String title;
    JFrame frame;

    /**
     * Class RTSPStreamWindow
     * <p>
     *     Creates a singular window where one RTSP stream can play.
     * </p>
     * @param address
     * @param title
     */
    RTSPStreamWindow(String address, String title){
        this.address = address;
        this.title = title;

        this.frame = new JFrame(this.title);

        this.mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        frame.setContentPane(this.mediaPlayerComponent);

        frame.setLocation(100, 100);
        frame.setSize(1050, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        this.mediaPlayerComponent.mediaPlayer().media().play(this.address);
    }

    public void setNewAddress(String address){
        this.address = address;
        this.mediaPlayerComponent.mediaPlayer().media().play(this.address);
    }

    public String getCurrentAddress(){
        return this.address;
    }

    public void setNewTitle(String title){
        this.title = title;
        this.frame.setTitle(title);
    }

    public String getCurrentTitle(){
        return this.title;
    }


}
