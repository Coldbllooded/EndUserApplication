package com.FerrisIOT;

public class Main {
    public static final String URL = "https://triagecore.com:8000/test";//Added global URL for easy access
    public static java.net.URL ActiveStream;
    public static Authenticator authenticator = null;
    public static void main(String[] args) {
	// write your code here

        Login.CreateLogin();
    }
}
