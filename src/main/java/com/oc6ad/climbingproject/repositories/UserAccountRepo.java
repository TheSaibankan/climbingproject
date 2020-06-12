package com.oc6ad.climbingproject.repositories;


import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepo extends CrudRepository<UserAccount, Long> {
}
