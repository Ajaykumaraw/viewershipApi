package com.picstle.viewerApi.model;


public class JwtResponse {

    private String Jwttoken;

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
