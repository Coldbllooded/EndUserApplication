package com.FerrisIOT;

public class Camera {
    String UCID;
    String Name;
    Camera(String UCID, String Name){
        this.UCID = UCID;
        this.Name = Name;
    }

    public String getUCID() {
        return UCID;
    }

    public void setUCID(String UCID) {
        this.UCID = UCID;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
