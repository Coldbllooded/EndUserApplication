package com.FerrisIOT;

import javax.swing.*;

public class IncorrectCredentials extends JFrame{
    private JPanel JPanel;
    private JLabel LabelIncorrect;
    IncorrectCredentials(){
        this.setContentPane(JPanel);
        this.setVisible(true);
        this.setSize(500, 320);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
