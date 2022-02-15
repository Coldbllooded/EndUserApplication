package com.FerrisIOT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Select extends JFrame {
    private JPanel choose;
    private JPanel Grid;

    public Select(LinkedList<Camera> Dname) {
        for (int x = 0; x <= Dname.size(); x++) {

            JButton NB = new JButton(Dname.get(x).getName());
            Grid.add(NB);
            NB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //Open link to device choosen
                }
            });


        }
        Grid.revalidate();
    }
}
