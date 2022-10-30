package com.picstle.viewerApi.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AppUser {

    @Id
    private String userName;
    private String email;
    private String category;
    private String password;
    private String role;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", category='" + category + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
