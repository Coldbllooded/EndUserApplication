package com.FerrisIOT;

import javax.swing.*;

public class Return {
    private JButton returnButton;
    private JPanel RET;
    public Return(){
        RET.setVisible(true);
        RET.setSize(30,20);
        returnButton.addActionListener(e ->{
            Login.ResetLogin();
        });
    }
}
