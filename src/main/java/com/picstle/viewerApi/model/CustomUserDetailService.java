package com.picstle.viewerApi.model;

import com.picstle.viewerApi.UserRepo.AppuserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AppuserRepo appuserRepo;

    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {
        System.out.println("in custom user details "+Username);
        AppUser user = appuserRepo.findByuserName(Username);
        System.out.println("data from repo "+user);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }

        return new CustomUserDetails(user);
    }
}
