package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.UserAccount;

public interface UserAccountService extends CrudService<UserAccount, Long> {
    void addUserAccount(UserAccount userAccount);
}
