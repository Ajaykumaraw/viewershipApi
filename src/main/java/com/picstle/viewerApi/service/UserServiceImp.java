package com.picstle.viewerApi.service;

import com.picstle.viewerApi.UserRepo.AppuserRepo;
import com.picstle.viewerApi.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private AppuserRepo appuserRepo;

    @Override
    public AppUser registerUser(AppUser appUser) {
        return appuserRepo.save(appUser);
    }
}
