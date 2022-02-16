package com.FerrisIOT.HTTP;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/**
 * <b>CLASS HTTPS</b>
 * <p><b>DESCRIPTION: </b>provides static methods for creating custom HTTPS post and get requests</p>
 * @author Aron
 * @version 1.0
 * @apiNote When creating headers, avoid having identical key with different values, as this will corrupt header assignment
 */
public class Https {

    /**
     * <b>RECORD REQUEST</b>
     * <p><b>DESCRIPTION: </b>Provides an object class that allows for separate storage of body and status with respective getters, but not setters.
     * @param status the status code generated
     * @param body the string that is given back from the responder
     */
    public record Request(int status, String body) {
        public int getStatus() {
            return status;
        }
        public String getBody() {
            return body;
        }
    }

    /**
     * <b>Https POST method formulator</b>
     * <p><b>DESCRIPTION: </b>Formulates a simple POST request with HTTPS to a local or non-local URL</p>
     * @param httpsURL - The URL to contact for the GET request. full format would include Https://<domain name, or IP address>:<port>
     * @param headers - An ordered list of headers to apply to the packet being sent
     * @param body - The string that should be sent to the receiver
     * @return - Request object with the corresponding response code as an integer, and body as a string
     */
    public static Request post(String httpsURL, String body, HashMap<String, String> headers) throws IOException {
        //Convert to URL
        URL myurl = new URL(httpsURL);
        //Open a connection
        HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
        //Set the type of request to POST
        con.setRequestMethod("POST");
        //Iteratively sets headers
        for (String key : headers.keySet()) con.setRequestProperty(key, headers.get(key));

        //Prepare the output stream and send the remote server the data
        DataOutputStream output = new DataOutputStream(con.getOutputStream());
        output.writeBytes(body);
        output.close();

        //Prepare to capture the input to us, from the remote connection
        DataInputStream input = new DataInputStream( con.getInputStream() );
        StringBuilder stringOut = new StringBuilder();
        //Build the final response
        for( int c = input.read(); c != -1; c = input.read() )
            stringOut.append(c);

        input.close();
        
        return new Request(con.getResponseCode(), stringOut.toString());
    }

    /**
     * <b>Https GET method formulator</b>
     *
     * <p><b>DESCRIPTION: </b>Formulates a simple GET request with HTTPS to a local or non-local URL</p>
     *
     * @param httpsURL The URL to contact for the GET request. full format would include Https://<domain name, or IP address>:<port>
     * @param headers An ordered list of headers to apply to the packet being sent
     * @return Request object with the corresponding response code as an integer, and body as a string
     */
    public static Request get(String httpsURL, HashMap<String, String> headers) throws IOException {
        //Convert to URL
        URL myurl = new URL(httpsURL);
        //Open a connection
        HttpsURLConnection  con = (HttpsURLConnection)myurl.openConnection();
        //Set the type of request to POST
        con.setRequestMethod("GET");

        //Iteratively sets headers
        for (String key : headers.keySet()) con.setRequestProperty(key, headers.get(key));

        //Prepare to capture the input to us, from the remote connection
        DataInputStream input = new DataInputStream(con.getInputStream());
        StringBuilder stringOut = new StringBuilder();

        for(int c = input.read(); c != -1; c = input.read()) stringOut.append(c);
        input.close();

        return new Request(con.getResponseCode(), stringOut.toString());
    }


}
