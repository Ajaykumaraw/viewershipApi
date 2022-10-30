package com.picstle.viewerApi.service;


import com.picstle.viewerApi.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    public AppUser registerUser(AppUser appUser);
}
