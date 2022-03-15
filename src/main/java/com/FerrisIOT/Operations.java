package com.FerrisIOT;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
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
    public static LinkedList<StationInfo> requestCameras(String session_key, int id) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        //Updated to be more efficient and easier to use

        //Generate Headers
        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "cams");

        Https.Request request = Https.post(Main.URL, id + "|" + session_key, headers);

        if (request.getStatus() == 200) {
            LinkedList<StationInfo> data = new LinkedList<>();
            String[] temp2 = request.getBody().split("\n");
            for (String indiv :temp2){
                String[] camera = indiv.split("\\|");
                //Now we have array index 0 of array 'camera' as always the UUID of the thing we are looking at, and index 1 as the camera name
                //Camera record is as follows: UUID, NAME
                data.add(new StationInfo(camera[0],camera[1],camera[2],camera[3],Main.authenticator.getUserID()));
            }
            return data;
        } else return null;
    }

    /**
     * <b>Request Connection</b>
     * <p>Given a camera and session key, get a direct address to the camera</p>
     * @param session_key the temporary string session key that allows the client to access services
     * @param stationInfo the camera in question to be found
     * @param id the user ID
     * @return A URL of the active stream
     * @throws IOException If the exchange between the server and the client fails or breaks
     */
    public static String requestConnection(String session_key, StationInfo stationInfo, int id) throws IOException, NoSuchAlgorithmException, KeyManagementException {

        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "stream");

        Https.Request request = Https.post(Main.URL, id + "|" + session_key + "|" + stationInfo.getUuid(), headers);

        //assume the only response is a standard URL format with IP or domain and port
        return String.valueOf(request.getBody());

    }

    /**
     * <b>RECORD Authentication</b>
     * <p>The Authentication Record is generated whenever the user logs in. Stored here is a set of values to use after a user authenticates</p>
     * @param isAuthenticated {@code TRUE} when the user authenticates, {@code FALSE} when there is an error
     * @param sessionKey The unique session key that generates when a user logs in, and is used for completing requests of various data
     * @param id The User's ID, unique to them
     * @param status_code The Status code of the POST made for the authentication. Used if {@code isAuthenticated} is {@code FALSE} to determine the cause of failure
     */


    /**
     * <b>METHOD AUTHENTICATE USER</b>
     * <p>Attempts to contact the main server for authentication, given a username and password</p>
     * @param username The user's username
     * @param password The user's password
     * @return An Authentication object filled with information about status, if authentication completed sucessfully, the user ID and the session key.
     * @throws IOException if the connection was broken or interrupted
     */
    public static Authenticator authenticateUser(String username, String password) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("request", "authentication");

        Https.Request request = Https.post(Main.URL, username + "|" + password, headers);

        int status = request.getStatus();
        boolean isAuthenticated = status == 200;

        if (request.getBody().equals("%INVALID")){
            System.out.println("Request to auth INVALID");
            return new Authenticator (-1, "-1", status, false);
        }
        int userID = Integer.parseInt(request.getBody().split("\n")[0]);
        String sessionKey = request.getBody().split("\n")[1];

        return new Authenticator (userID, sessionKey, status, isAuthenticated);
    }


}
