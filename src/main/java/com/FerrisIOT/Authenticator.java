package com.FerrisIOT;



public class Authenticator {
    private final int userID;
    private final String sessionKey;
    private final int status;
    private final boolean isAuthenticated;

    Authenticator(int userID, String sessionKey, int status, boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated;
        this.status = status;
        this.sessionKey = sessionKey;
        this.userID = userID;
    }


    public int getUserID() {
        return userID;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public int getStatus() {
        return status;
    }

}
