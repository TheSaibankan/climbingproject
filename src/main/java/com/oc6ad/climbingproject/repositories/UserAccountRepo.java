package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Long> {
    UserAccount findByLogin(String login);
}
