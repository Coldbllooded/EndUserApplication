package com.FerrisIOT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Select extends JFrame {
    private JPanel choose;
    public  JPanel Grid;
    Select(LinkedList<Camera> Cameras){
        Grid(Cameras);
        this.setContentPane(choose);
        this.setSize(1000,500);
        this.setVisible(true);

    }
    public void Grid(LinkedList<Camera> Dname) {
        for (Camera camera : Dname) {
            System.out.println(camera.getName());
            JButton NB = new JButton(camera.getName());
            System.out.println(NB);
            Grid.add(NB);

            NB.addActionListener(e -> {
                //Call Function to close
                try {
                    Main.ActiveStream = Operations.requestConnection(Main.authenticator.getSessionKey(), camera, Main.authenticator.getUserID());

                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    ex.printStackTrace();
                }

            });


        }
        Grid.revalidate();
        Grid.setVisible(true);
        choose.setVisible(true);
    }
}
