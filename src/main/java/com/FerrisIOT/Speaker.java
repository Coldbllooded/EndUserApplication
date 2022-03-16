package com.FerrisIOT;

import javax.swing.*;

public class Speaker extends JFrame{
    private JPanel Sounds;
    Speaker()
    {
        JPanel SpeakerGrid = new JPanel();
        //Generate Grid of playable noises
        JButton Bleep = new JButton();
        Bleep.setText("Bleep");
        Bleep.addActionListener(e ->{
            //Method to send signal to tell speaker to play a certain sound effect
        });
        //Set Panel size
        SpeakerGrid.setSize(100,100);
        //Make panel visible
        SpeakerGrid.setVisible(true);
        //Revalidate Grid
        SpeakerGrid.revalidate();
    }
}
