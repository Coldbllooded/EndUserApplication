package com.FerrisIOT;

import java.util.UUID;

public class Authenticator {
    private int userID = -1;
    private UUID sessionKey = null;

    Authenticator(){};
    Authenticator(int userID, String sessionKey){
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
}
