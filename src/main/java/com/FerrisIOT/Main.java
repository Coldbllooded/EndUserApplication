package com.FerrisIOT;

import com.sun.security.jgss.InquireSecContextPermission;

import javax.media.protocol.PullBufferDataSource;
import javax.swing.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.TimeZone;

public class Main {
    public static String URL = "https://10.35.80.183:8000/test";//Added global URL for easy access
    public static Operations.Authentication Authentication;
    public static java.net.URL ActiveStream;
    public static Authenticator authenticator = new Authenticator();
    public static void main(String[] args) {
	// write your code here
        Login.CreateLogin();

    }
}
