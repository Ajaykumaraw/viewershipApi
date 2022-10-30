package com.picstle.viewerApi.UserRepo;


import com.picstle.viewerApi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppuserRepo extends JpaRepository<AppUser,String> {

    AppUser findByuserName(String userName);
}
