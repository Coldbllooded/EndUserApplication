package com.FerrisIOT;

import javax.swing.*;

public class NoSpeaker extends JPanel {
    NoSpeaker()
    {
        JPanel NoSpeakers = new JPanel();
        JTextPane Message = new JTextPane();
        Message.setText("There are no Speakers connected to this station.");
        NoSpeakers.add(Message);
        NoSpeakers.setSize(100,100);
        NoSpeakers.setVisible(true);
    }
}
