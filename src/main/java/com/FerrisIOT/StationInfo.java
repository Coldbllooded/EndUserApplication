package com.FerrisIOT;

public class StationInfo {
    private final String uuid;
    private final String Name;
    private final String Auth;
    private final String CamStream;
    //private final String WeatherStream;
    //private final String Speaker;
    private final String Status;
    private final int Owner;

    StationInfo(String uuid, String name, String Auth, String Status, int Owner){
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

    public String getAuth() { return this.Auth; }

    public String getCamStream() { return  this.CamStream; }

    public String getStatus() { return this.Status; }

    //public String getWeatherStream() { return this.WeatherStream; }

    //public String getSpeaker() { return  this.Speaker; }

}
