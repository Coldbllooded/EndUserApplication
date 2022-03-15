package com.FerrisIOT;

import javax.swing.*;

public class Stream extends JFrame {
    private JPanel Box;
    private JFrame InBOX;

    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak)
    {
        InBOX = new JFrame();
        //Create Camera Stream
        System.out.println(sInfo.getCamStream());
        RTSPStreamContainer x = new RTSPStreamContainer(sInfo.getCamStream()+Cam,1000,100);
        InBOX.add(x);

        //Create Weather Stream
        //Weather(sInfo./*New Method*/)
        //Create Speaker Grid
        //Speaker(sInfo./*New Method*/)
        //Create Memory Used Form
        JTextPane Percent = new JTextPane();
        Percent.setText(Memory + "%");
        InBOX.add(Percent);
        InBOX.setVisible(true);
        x.playStream();

    }

}
