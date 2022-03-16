package com.FerrisIOT;

import javax.swing.*;

public class NoSensors extends JPanel{
    NoSensors()
    {
        JPanel No = new JPanel();
        JTextPane Text = new JTextPane();
        Text.setText("No Weather Sensors are Connected");
        No.add(Text);
        No.setVisible(true);
    }
}
