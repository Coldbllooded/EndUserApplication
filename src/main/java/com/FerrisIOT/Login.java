package com.FerrisIOT;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Handler;

public class Login extends JFrame {
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JButton enterButton;
    private JPanel Log;

    Login(){
        try {
            System.out.println(Https.get(Main.URL, new HashMap<>()).getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
        this.setContentPane(Log);
        this.setSize(new Dimension(1000, 600));
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
                        Main.Authentication = Operations.authenticateUser(U, P);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    assert Main.Authentication != null;
                    if (!Main.Authentication.isAuthenticated())
                    {
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
                            LinkedList Cams = Operations.requestCameras(SID, UID);
                            //Create camera list
                            new Select(Cams);
                            //Use return from Select to establish connection

                        } catch (IOException ex) {
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
                Main.Authentication = Operations.authenticateUser(U, P);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            assert Main.Authentication != null;
            if (!Main.Authentication.isAuthenticated())
            {
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
                    LinkedList<Camera> Cams = Operations.requestCameras(SID, UID);
                    //Create camera list
                    new Select(Cams);
                    //Use return from Select to establish connection

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    public static void CreateLogin()
    {
        new Login();
    }
    public static void ResetLogin()
    {
        System.exit(0);
    }
}
