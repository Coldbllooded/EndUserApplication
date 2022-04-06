package com.FerrisIOT;

public class StationInfo {
    private final String uuid;
    private final String Name;
    private final String basePass;
    private final String CamStream;
    private final String Status;
    private final int Owner;

    StationInfo(String uuid, String name, String basePass, String Status, int Owner){
        this.uuid = uuid;
        this.Name = name;
        this.basePass = basePass;
        this.Status = Status;
        this.Owner = Owner;

        CamStream = "rtsp://triagecore.com:8554/" + this.uuid + "/";
    }

    public String getUuid() { return this.uuid; }

    public String getName(){
        return this.Name;
    }

    public String getBasePass() { return this.basePass; }

    public String getCamStream() { return  this.CamStream; }

    public String getStatus() { return this.Status; }

}
