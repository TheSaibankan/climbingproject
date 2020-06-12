package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.UserAccount;

import java.util.List;
import java.util.Optional;

public interface UserAccountServ {

     List<UserAccount> listAllUsers();

     Optional<UserAccount> findWithId(Long id);

     UserAccount updateAndSave(UserAccount userAccount);

     void delete(Long id);

     void deleteAll();
}
