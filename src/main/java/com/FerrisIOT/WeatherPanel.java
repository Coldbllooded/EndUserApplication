package com.FerrisIOT;

import javax.swing.*;

import static org.apache.commons.lang3.StringUtils.chop;

public class WeatherPanel extends JPanel{
    private JLabel TempLabel;
    private JLabel ActualTemp;
    private JLabel PressureLabel;
    private JLabel ActualPressure;
    private JLabel HumidityLabel;
    private JLabel ActualHumidity;
    private JPanel Temperature;
    private JPanel Pressure;
    private JPanel Humidity;
    private JPanel InfoPane;


    WeatherPanel()
    {
        this.setSize(100,100);
        this.setVisible(true);

    }
    public void setWeatherData(String WIN){
        String[] WINS;
        WINS = WIN.split("\\|");
        String Press = WINS[0];
        String Temp = WINS[1];
        String Humidity = WINS[2];
        Humidity = chop(Humidity);
        Humidity = chop(Humidity);
        ActualPressure.setText(Press);
        ActualTemp.setText(Temp);
        ActualHumidity.setText(Humidity);
    }
}
