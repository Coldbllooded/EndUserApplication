package com.FerrisIOT;

import com.FerrisIOT.Https;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

//Updated class name to OPERATIONS because if we want to put more things here that do more things, and those things perform http operations, hence OPERATIONS

public class Operations {

    static String url = "https://10.35.81.223:8000/test";

    /**
     * <b>Request Cameras</b>
     * <p>Given a session key, grab all cameras associated with the user</p>
     * @param session_key the temporary string session key that allows the client to access services
     * @return A linked list of camera objects that contain names and unique IDs
     * @param id the user ID
     * @throws IOException If the exchange between the server and the client fails or breaks
     */
    public static LinkedList<Camera> requestCameras(String session_key, int id) throws IOException {
        //Updated to be more efficient and easier to use

        //Generate Headers
        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "cams");

        Https.Request request = Https.post(url, id + "|" + session_key, headers);

        if (request.getStatus() == 200) {
            LinkedList<Camera> data = new LinkedList<>();
            String[] temp2 = request.getBody().split("\n");
            for (String indiv :temp2){
                String[] camera = indiv.split("\\|");
                //Now we have array index 0 of array 'camera' as always the UUID of the thing we are looking at, and index 1 as the camera name
                data.add(new Camera(camera[0],camera[1]));
            }
            return data;
        } else return null;
        /*
        //Data Request
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost lis = new HttpPost("https://10.35.81.223:8000/test");
        //Set Header
        lis.addHeader("Request", "cams");
        //Set Body
        lis.setEntity(new StringEntity(session_key));
        //Data Response
        client.execute(lis);


        String temp = EntityUtils.toString(lis.getEntity());



        return data;

         */
    }

    /**
     * <b>Request Connection</b>
     * <p>Given a camera and session key, get a direct address to the camera</p>
     * @param session_key the temporary string session key that allows the client to access services
     * @param camera the camera in question to be found
     * @param id the user ID
     * @return A URL of the active stream
     * @throws IOException If the exchange between the server and the client fails or breaks
     */
    public static URL requestConnection(String session_key, Camera camera, int id) throws IOException {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "stream");

        Https.Request request = Https.post(url, id + "|" + session_key + "\\|" + camera.getUCID(), headers);

        //assume the only response is a standard URL format with IP or domain and port
        return new URL(request.getBody());

    }

    //TODO: authentication handler, status request,

}
