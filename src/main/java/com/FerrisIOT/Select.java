package com.FerrisIOT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

public class Select extends JFrame {
    private JPanel choose;
    public  JPanel Grid;
    Select(LinkedList<Camera> Cameras){
        Grid(Cameras);
    }
    public void Grid(LinkedList<Camera> Dname) {
        Grid.add(new Return(this));
        for (int x = 0; x <= Dname.size(); x++) {
            JButton NB = new JButton(Dname.get(x).getName());
            Grid.add(NB);
            int finalX = x;
            NB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Call Function to close
                    try {
                        Main.ActiveStream = Operations.requestConnection(Main.Authentication.getSessionKey(), Dname.get(finalX), Main.Authentication.getID());

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            });


        }
        Grid.revalidate();
    }
}
