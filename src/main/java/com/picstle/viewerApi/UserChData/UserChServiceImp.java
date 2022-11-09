package com.picstle.viewerApi.UserChData;

import com.picstle.viewerApi.UserRepo.UserChDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserChServiceImp implements UserChService{

    @Autowired
    private UserChDataRepo userChDataRepo;

    @Override
    public UserChData saveUserChData(UserChData userChData) {

        return userChDataRepo.save(userChData);
    }
}
