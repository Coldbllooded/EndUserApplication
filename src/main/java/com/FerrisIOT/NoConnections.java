package com.FerrisIOT;

import javax.swing.*;

public class NoConnections extends JFrame{
    private JPanel panel1;
    private JTextPane noFerrisIOTSecurityTextPane;
    NoConnections()
    {
        this.setContentPane(panel1);
        this.setSize(500,170);
        this.setVisible(true);
        new Return(this);
    }
}
