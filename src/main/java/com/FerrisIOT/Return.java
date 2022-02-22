package com.FerrisIOT;

import javax.swing.*;

public class Return extends JButton{
    private JButton returnButton;
    public Return(JFrame Current){//Need to pass JFrame name
        returnButton.setVisible(true);
        returnButton.setSize(50,30);
        returnButton.setText("Return");
        returnButton.addActionListener(e ->{
            Current.dispose();
        });
    }
}
