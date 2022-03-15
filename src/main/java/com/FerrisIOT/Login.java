package com.FerrisIOT;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;

public class Login extends JFrame {
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JButton enterButton;
    private JPanel Log;

    Login(){
        try {
            System.out.println(Https.get(Main.URL, new HashMap<>()).getBody());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        Image Icon = new javax.swing.ImageIcon("C:\\Users\\darth\\Desktop\\Senior Projects\\Bulldog.png").getImage();
        this.setIconImage(Icon);
        enterButton.setBorder(new AbstractBorder() {
            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                super.paintBorder(c, g, x, y, width, height);
            }
        });
        this.setVisible(true);
        this.setContentPane(Log);
        this.setSize(new Dimension(1000, 500));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formattedTextField2.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    String U = formattedTextField1.getText();
                    String P = formattedTextField2.getText();
                    //Removed URL from this context for easy manipulation

                    try {
                        Main.authenticator = Operations.authenticateUser(U, P);
                    } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                        ex.printStackTrace();
                    }

                    assert Main.authenticator != null;
                    if (!Main.authenticator.isAuthenticated())
                    {
                        System.out.println("Incorrect login detected");
                        new IncorrectCredentials();
                    }
                    else
                    {
                        //User and Session ID
                        int UID = Main.authenticator.getUserID();
                        String SID = Main.authenticator.getSessionKey();
                        assert SID != null;
                        assert UID != -1;
                        try {
                            LinkedList<StationInfo> Cams = Operations.requestCameras(SID, UID);
                            //Create camera list
                            new Select(Cams);
                            //Use return from Select to establish connection

                        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                            ex.printStackTrace();
                        }
                    }

                }
            }
        });
        enterButton.addActionListener(e -> {
            //Get values from form
            //Check Credentials
            String U = formattedTextField1.getText();
            String P = formattedTextField2.getText();

            try {
                Main.authenticator = Operations.authenticateUser(U, P);
            } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            }

            assert Main.authenticator != null;
            if (!Main.authenticator.isAuthenticated())
            {
                System.out.println("Incorrect login detected");
                new IncorrectCredentials();
            }
            else
            {
                //User and Session ID
                int UID = Main.authenticator.getUserID();
                String SID = Main.authenticator.getSessionKey();
                assert SID != null;
                assert UID != -1;
                try {
                    LinkedList<StationInfo> Cams = Operations.requestCameras(SID, UID);
                    //Create camera list
                    new Select(Cams);
                    //Use return from Select to establish connection

                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void CreateLogin()
    {
        new Login();
    }
}
