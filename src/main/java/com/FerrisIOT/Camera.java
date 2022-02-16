package com.FerrisIOT;

public class Camera {
    private String UCID;
    private String Name;
    Camera(String uuid, String name){
        this.UCID = uuid;
        this.Name = name;
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
