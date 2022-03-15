package com.FerrisIOT;

import javax.swing.*;

public class Cam extends JFrame {
    private JPanel View;
    Cam(String STID, String STIT)
    {
        new RTSPStreamWindow(STID,STIT);
    }
}
