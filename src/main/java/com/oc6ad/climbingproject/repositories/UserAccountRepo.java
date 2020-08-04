package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends CrudRepository<UserAccount, Long> {
    /**
     * Retrieve an account via the login
     * @param login
     * @return UserAccount
     */
    UserAccount findByLogin(String login);


    /**
     * Check the existence of an account
     * @param login
     * @return Boolean
     */
    Boolean existsByLogin(String login);
}
