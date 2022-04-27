package com.FerrisIOT;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.TimerTask;

import static java.awt.Font.BOLD;
import static org.apache.commons.lang3.StringUtils.chop;

public class Stream extends JFrame {
    private JPanel Box;
    private JPanel Camera;
    private JPanel WeatherInfo;
    private JPanel SpeakerButton;
    private JPanel PirSensor;
    RTSPStreamContainer x = null;
    JLabel Pressure = new JLabel();
    JLabel Temperature = new JLabel();
    JLabel Humidity = new JLabel();
    StationInfo sInfo;
    JLabel Motion = new JLabel();
    ImageIcon redTriangle = new ImageIcon(Objects.requireNonNull(getClass().getResource("/SmallredTriangle.png")));
    ImageIcon greenTriangle = new ImageIcon(Objects.requireNonNull(getClass().getResource("/SmallgreenTriangle.png")));



    Stream(StationInfo sInfo, int Memory, int Cam, int Weat, int Speak) throws IOException, NoSuchAlgorithmException, KeyManagementException, MqttException {
        ImageIcon WhiteSpace = new ImageIcon(Objects.requireNonNull(getClass().getResource("/WhiteSpace.png")));
        Font Type1 = new Font("Bernard MT Condensed", BOLD, 20);
        Motion.setIcon(greenTriangle);
        PirSensor.add(Motion);
        PirSensor.revalidate();
        //Create Camera Stream
        this.sInfo = sInfo;
        if(Cam > 0)
        {

            System.out.println(sInfo.getCamStream());
            this.x = new RTSPStreamContainer((sInfo.getCamStream() + "S0"), 1000, 1000);
            x.setResizable(true);
            x.setBorder(null);
            x.setFrameIcon(WhiteSpace);
            x.setClosable(false);
            Camera.add(x);

        }
        else
        {
            JLabel NOCAMS = new JLabel("No Camera Streams are Available", SwingConstants.CENTER);
            NOCAMS.setForeground(Color.BLACK);
            NOCAMS.setFont(Type1);
            Camera.setBackground(Color.GRAY);
            Camera.setSize(1000,1000);
            Camera.add(NOCAMS);
        }
        //Create Speaker Button
        if(Speak > 0)
        {
            JButton PLAY = new JButton("Play Sound");
            PLAY.setFont(Type1);
            PLAY.setBackground(Color.GRAY);
            PLAY.addActionListener(e-> {
                try {
                    Operations.playSpeaker(sInfo.getBasePass(),sInfo.getUuid());
                } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
                    ex.printStackTrace();
                }
            });
            SpeakerButton.add(PLAY);

        }
        else
        {
            JLabel NOSPEAKER = new JLabel("No Speakers are Available");
            NOSPEAKER.setForeground(Color.BLACK);
            SpeakerButton.setBackground(Color.GRAY);
            NOSPEAKER.setFont(Type1);
            SpeakerButton.add(NOSPEAKER);
        }
        //Create Weather Info Section
        if (Weat > 0) {

            Pressure.setFont(Type1);
            Pressure.setForeground(Color.BLACK);

            Temperature.setFont(Type1);
            Temperature.setForeground(Color.BLACK);

            Humidity.setFont(Type1);
            Humidity.setForeground(Color.BLACK);

            Timer timer = new Timer(1000,e -> {
                WEATHERUPDATE();
            });

            WeatherInfo.setBackground(Color.white);
            WeatherInfo.add(Pressure);
            WeatherInfo.add(Temperature);
            WeatherInfo.add(Humidity);
            timer.start();

        }
        else {
            JLabel NOWEATHER = new JLabel("No Weather Info is Available");
            NOWEATHER.setForeground(Color.BLACK);
            NOWEATHER.setFont(Type1);
            WeatherInfo.setBackground(Color.GRAY);
            WeatherInfo.add(NOWEATHER);
        }
        //PIR Sensor
        MqttConnectOptions options;
        options = new MqttConnectOptions();
        options.setUserName("DEFAULT_USER");
        options.setAutomaticReconnect(true);
        options.setCleanSession(true);
        options.setConnectionTimeout(10);

        MqttClient client = new MqttClient("tcp://" + "triagecore.com" + ":1883", "DEFAULT_USER" );
        client.connect(options);
        System.out.println("MQTT system inititated");

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGreen();
                Timer testing = (Timer) e.getSource();
                testing.stop();
            }
        });


        IMqttMessageListener listener = (topic, message) -> {
            System.out.println("Topic: " + topic + ", Message: " + message);
            //Change the jlabel image here
            System.out.println("Motion Detected");
            setRed();
            System.out.println("Set red");
            timer.restart();
            System.out.println("Cancelled Timer");

            System.out.println("Reached end of routine");
        };

        client.subscribe(sInfo.getUuid() + "-M", 0, listener);
        System.out.println("MQTT Client subscribed with topic " + sInfo.getUuid() + "-M");
        PirSensor.setVisible(true);

        //Main Container Set Visible and Minimum size
        Image icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/Bulldog.png"))).getImage();
        this.setIconImage(icon);
        this.setSize(1500,1030);
        this.setMinimumSize(new Dimension(700,1000));
        this.setContentPane(Box);
    }

    public void STREAMPLAY(){
        if(x != null)
        {
            x.playStream();
        }
    }

    public static class WeatherInfoData{

        public String pressure, temperature, humidity;

        WeatherInfoData(String weatherInfo) {
            String[] temp = weatherInfo.split("\\|");
            pressure = temp[0];
            temperature = temp[1];
            humidity = temp[2];
            humidity = chop(humidity);
            humidity = chop(humidity);
        }


    }

    public void WEATHERUPDATE(){
        System.out.println(System.currentTimeMillis() /1000 + " UPDATED WEATHER");
        String WIN = null;
        try {
            WIN = Operations.requestWeather(sInfo.getBasePass(), Main.authenticator.getSessionKey(), sInfo.getUuid(), Main.authenticator.getUserID());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException ex) {
            ex.printStackTrace();
        }

        assert WIN != null;
        WeatherInfoData data = new WeatherInfoData(WIN);

        Humidity.setText("Humidity: " + data.humidity + "%");
        Pressure.setText("Pressure: " + data.pressure);
        Temperature.setText("Temperature: " + data.temperature + "F");

        WeatherInfo.revalidate();
    }

    public void setRed(){
        Motion.setIcon(redTriangle);
        Box.revalidate();
    }

    public void setGreen(){
        Motion.setIcon(greenTriangle);
        Box.revalidate();
    }
}
