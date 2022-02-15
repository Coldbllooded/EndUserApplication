package com.FerrisIOT;

import com.FerrisIOT.HTTP.HttpController;

import javax.media.StopEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login extends JFrame {
    private JFormattedTextField formattedTextField1;
    private JFormattedTextField formattedTextField2;
    private JButton enterButton;
    private JPanel Log;
    public String Stat = "Null";

    Login(){
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
                    Stat = (HttpController.HTTPC(U, P));
                    System.out.println(Stat);
                    if (Stat == "fail")
                    {
                        new IncorrectCredentials();
                    }
                    else if(Stat == "ok")
                    {
                        new CameraGrid();
                    }
                    else if(Stat == "")
                    {
                        new NoConnections();
                    }

                }
            }
        });
        enterButton.addActionListener(e -> {
            //Get values from form
            //Check Credentials
            String U = formattedTextField1.getText();
            String P = formattedTextField2.getText();
            Stat = HttpController.HTTPC(U, P);
            if (Stat == "fail")
            {
                new IncorrectCredentials();
            }
            else if(Stat == "ok")
            {
                new CameraGrid();
            }
            else
            {
                new NoConnections();
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
