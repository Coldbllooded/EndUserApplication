package com.FerrisIOT.HTTP;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class https {

    public static String sendPost(String httpsURL, String body) {
        URL myurl = null;
        HttpsURLConnection con = null;
        try {
            myurl = new URL(httpsURL);
            con = (HttpsURLConnection)myurl.openConnection();
            con.setRequestMethod("POST");
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataOutputStream output = null;
        StringBuilder stringOut = new StringBuilder();
        try {
            output = new DataOutputStream(con.getOutputStream());
            output.writeBytes(body);
            output.close();
            DataInputStream input = new DataInputStream( con.getInputStream() );

            for( int c = input.read(); c != -1; c = input.read() )
                stringOut.append(c);

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return String.valueOf(stringOut);
    }

    public static String httpsGet(String httpsURL){
        URL myurl = null;
        HttpsURLConnection con = null;
        try {
            myurl = new URL(httpsURL);
            con = (HttpsURLConnection)myurl.openConnection();
            con.setRequestMethod("GET");
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataInputStream input = null;
        StringBuilder stringOut = new StringBuilder();
        try {
            input = new DataInputStream( con.getInputStream() );
            for( int c = input.read(); c != -1; c = input.read() )
                stringOut.append(c);

            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return String.valueOf(stringOut);
    }
}
