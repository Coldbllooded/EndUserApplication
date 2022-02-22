package com.FerrisIOT;

import java.util.UUID;

public class Authenticator {
    private int userID;
    private UUID sessionKey;
    private int status;
    private boolean isAuthenticated;

    Authenticator(int userID, String sessionKey, int status, boolean isAuthenticated){
        this.isAuthenticated = isAuthenticated;
        this.status = status;
        this.sessionKey = UUID.fromString(sessionKey);
        this.userID = userID;
    }


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getSessionKey() {
        return sessionKey.toString();
    }

    public void setSessionKey(UUID sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
