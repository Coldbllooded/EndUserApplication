package com.FerrisIOT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class Select extends JFrame {
    private JPanel choose;
    private JPanel Grid;

    public Select(LinkedList<String> Dname) {
        for (int x = 0; x <= Dname.size(); x++) {
            JButton NB = new JButton((String) Dname.get(x));
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
