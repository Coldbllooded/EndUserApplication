package com.FerrisIOT;

import javax.swing.*;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;

public class Select extends JFrame {
    private JPanel choose;
    public  JPanel Grid;
    private JButton Close;

    Select(LinkedList<Camera> Cameras){
        Grid(Cameras);
        Close.addActionListener(e-> {
            this.dispose();
        });
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
                String IPadd = null;
                try {
                    IPadd = Operations.requestConnection(Main.authenticator.getSessionKey(), camera, Main.authenticator.getUserID());
                    System.out.println(IPadd);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (NoSuchAlgorithmException ex) {
                    ex.printStackTrace();
                } catch (KeyManagementException ex) {
                    ex.printStackTrace();
                }
                new CameraStream(IPadd);

            });


        }
        Grid.revalidate();
        Grid.setVisible(true);
        choose.setVisible(true);
    }
}
