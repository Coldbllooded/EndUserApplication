package com.FerrisIOT;

import javax.swing.*;

public class IncorrectCredentials extends JFrame{
    private JPanel JPanel;
    private JLabel LabelIncorrect;
    private JButton Close;

    IncorrectCredentials(){
        this.setContentPane(JPanel);
        Close.addActionListener(e-> {
            this.dispose();
        });
        this.setVisible(true);
        this.setSize(500, 320);
    }
}
