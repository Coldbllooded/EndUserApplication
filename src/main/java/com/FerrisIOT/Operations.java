package com.FerrisIOT;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;

//Updated class name to OPERATIONS because if we want to put more things here that do more things, and those things perform http operations, hence OPERATIONS

public class Operations {

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

        Https.Request request = Https.post(Main.URL, id + "|" + session_key, headers);

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
        lis.setEntity(new StringEntity(sessionKey));
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

        Https.Request request = Https.post(Main.URL, id + "|" + session_key + "\\|" + camera.getUCID(), headers);

        //assume the only response is a standard URL format with IP or domain and port
        return new URL(request.getBody());

    }

    /**
     * <b>RECORD Authentication</b>
     * <p>The Authentication Record is generated whenever the user logs in. Stored here is a set of values to use after a user authenticates</p>
     * @param isAuthenticated {@code TRUE} when the user authenticates, {@code FALSE} when there is an error
     * @param sessionKey The unique session key that generates when a user logs in, and is used for completing requests of various data
     * @param id The User's ID, unique to them
     * @param status_code The Status code of the POST made for the authentication. Used if {@code isAuthenticated} is {@code FALSE} to determine the cause of failure
     */
    public record Authentication(boolean isAuthenticated, int status_code, int id, String sessionKey){
        public int getStatusCode() { return status_code; }
        public boolean isAuthenticated() { return isAuthenticated; }
        public int getID() { return this.id; }
        public String getSessionKey() { return this.sessionKey; }
    }

    /**
     * <b>METHOD AUTHENTICATE USER</b>
     * <p>Attempts to contact the main server for authentication, given a username and password</p>
     * @param username The user's username
     * @param password The user's password
     * @return An Authentication object filled with information about status, if authentication completed sucessfully, the user ID and the session key.
     * @throws IOException if the connection was broken or interrupted
     */
    public static Authentication authenticateUser(String username, String password) throws IOException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "authentication");

        Https.Request request = Https.post(Main.URL, username + "|" + password, headers);

        int status = request.getStatus();
        boolean isAuthenticated = status == 200;
        int userID = Integer.parseInt(request.getBody().split("\n")[0]);
        String sessionKey = request.getBody().split("\n")[1];

        return new Authentication(isAuthenticated, request.getStatus(), userID, sessionKey);
    }


}
