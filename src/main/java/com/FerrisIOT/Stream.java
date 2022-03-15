package com.FerrisIOT;

import javax.swing.*;

public class Stream extends JFrame {
    private JPanel Box;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak)
    {
        //Create Camera Stream
        Box.add(new Cam(sInfo.getCamStream()+Cam, sInfo.getName()));
        //Create Weather Stream
        //Weather(sInfo./*New Method*/)
        //Create Speaker Grid
        //Speaker(sInfo./*New Method*/)
        //Create Memory Used Form
        JTextPane Percent = new JTextPane();
        Percent.setText(Memory + "%");
        Box.add(Percent);
        Box.setVisible(true);

    }

}
