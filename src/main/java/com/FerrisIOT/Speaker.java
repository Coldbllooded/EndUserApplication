package com.FerrisIOT;

import javax.swing.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.atomic.AtomicInteger;

public class Speaker extends JFrame{
    private JPanel Sounds;
    Speaker(String basePass, int speakerNumber)
    {
        JPanel SpeakerGrid = new JPanel();
        //Generate Grid of playable noises
        AtomicInteger sound = new AtomicInteger();
        JButton Bleep = new JButton();
        Bleep.setText("Bleep");
        Bleep.addActionListener(e ->{
            sound.set(1);
            try {
                Operations.playSpeaker(sound, basePass, speakerNumber);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (KeyManagementException ex) {
                ex.printStackTrace();
            }
        });
        JButton Moow = new JButton();
        Moow.setText("Moow");
        Moow.addActionListener(e ->{
            sound.set(2);
            try {
                Operations.playSpeaker(sound, basePass, speakerNumber);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (KeyManagementException ex) {
                ex.printStackTrace();
            }
        });
        JButton No = new JButton();
        No.setText("No");
        No.addActionListener(e ->{
            sound.set(3);
            try {
                Operations.playSpeaker(sound, basePass, speakerNumber);
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (KeyManagementException ex) {
                ex.printStackTrace();
            }
        });
        //Set Panel size
        SpeakerGrid.setSize(100,100);
        //Make panel visible
        SpeakerGrid.setVisible(true);
        //Revalidate Grid
        SpeakerGrid.revalidate();
    }
}
