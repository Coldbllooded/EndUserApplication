package com.FerrisIOT;

public class Camera {
    private String uuid;
    private String Name;
    Camera(String uuid, String name){
        this.uuid = uuid;
        this.Name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName(){
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
