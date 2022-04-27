package com.FerrisIOT;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Login extends JFrame {
    private JFormattedTextField formattedTextField1;
    private JButton enterButton;
    private JPanel Log;
    private JPasswordField passwordField1;
    private JLabel Pass;
    private JLabel User;
    private JPanel UserSection;
    private JPanel PasswordSection;
    private JLabel Logo;
    private JPanel LOGO;

    Login(){

        try {
            System.out.println(Https.get(Main.URL, new HashMap<>()).getBody());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        //Image Icon = new ImageIcon("C:\\Users\\darth\\Desktop\\Senior Projects\\Bulldog.png").getImage();
        Image icon = new javax.swing.ImageIcon(Objects.requireNonNull(getClass().getResource("/Bulldog.png"))).getImage();
        this.setIconImage(icon);

        Logo.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/FerrisIOTShieldLogo.png"))));

        this.setVisible(true);
        this.setContentPane(Log);
        this.setMinimumSize(new Dimension(700,500));
        this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        passwordField1.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    String U = formattedTextField1.getText();
                    String P = passwordField1.getText();
                    //Removed URL from this context for easy manipulation

                    try {
                        Main.authenticator = Operations.authenticateUser(U, P);
                    } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(Log, "Incorrect Login Credentials");
                    }

                    assert Main.authenticator != null;
                    if (!Main.authenticator.isAuthenticated())
                    {
                        //System.out.println("Incorrect login detected");
                        JOptionPane.showMessageDialog(Log, "Incorrect Login Credentials");
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
            String P = passwordField1.getText();

            try {
                Main.authenticator = Operations.authenticateUser(U, P);
            } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Incorrect Login Credentials");

            }

            assert Main.authenticator != null;
            if (!Main.authenticator.isAuthenticated())
            {
                //System.out.println("Incorrect login detected");
                JOptionPane.showMessageDialog(this, "Incorrect Login Credentials");
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
    public static void CreateLogin() {
        FlatLightLaf.setup();
        new Login();
    }
}
