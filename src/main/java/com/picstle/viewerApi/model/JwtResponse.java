package com.picstle.viewerApi.model;


public class JwtResponse {

    private String Jwttoken;

    private String userName;



    public JwtResponse(String jwttoken, String userName) {
        Jwttoken = jwttoken;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public JwtResponse() {
    }

    public JwtResponse(String token) {
        this.Jwttoken = token;
    }

    public String getToken() {
        return Jwttoken;
    }

    public void setToken(String token) {
        this.Jwttoken = token;
    }
}
