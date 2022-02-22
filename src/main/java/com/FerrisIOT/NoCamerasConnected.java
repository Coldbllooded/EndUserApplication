package com.FerrisIOT;

import javax.swing.*;

public class NoCamerasConnected extends JFrame{
    private JPanel panel1;
    private JTextPane thereAreNoCamerasTextPane;
    NoCamerasConnected()
    {
        this.setContentPane(panel1);
        this.setSize(165,285);
        this.setVisible(true);
        new Return(this);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
