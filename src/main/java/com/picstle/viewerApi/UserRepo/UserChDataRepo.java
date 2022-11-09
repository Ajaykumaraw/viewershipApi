package com.picstle.viewerApi.UserRepo;

import com.picstle.viewerApi.UserChData.UserChData;
import com.picstle.viewerApi.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserChDataRepo extends JpaRepository<UserChData,String> {
    UserChData findByuserName(String userName);
}
