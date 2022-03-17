package com.FerrisIOT;

import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;

public class RTSPStreamContainer extends JPanel {

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


    /**
     * Class RTSPStreamWindow
     * <p>
     *     Creates a singular window where one RTSP stream can play.
     * </p>
     * @param address
     * @param size
     */
    RTSPStreamContainer(String address, Dimension size){
        this.address = address;
        this.mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        super.add(this.mediaPlayerComponent);

        super.setSize(size);
        super.setVisible(true);
    }

    RTSPStreamContainer(String address, int width, int height){
        this.address = address;
        this.mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        super.add(this.mediaPlayerComponent);

        super.setSize(width, height);
        super.setVisible(true);
    }


    public void playStream(){
        this.mediaPlayerComponent.mediaPlayer().media().play(this.address);
    }

    public void setNewAddress(String address){
        this.address = address;
        this.mediaPlayerComponent.mediaPlayer().media().play(this.address);
    }

    public String getCurrentAddress(){
        return this.address;
    }


}
