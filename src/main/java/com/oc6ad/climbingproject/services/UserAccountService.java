package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.UserAccount;

/**
 * Interface gestion blabla
 */
public interface UserAccountService extends CrudService<UserAccount, Long> {
    void addUserAccount(UserAccount userAccount);

    UserAccount loadUserByLogin(String login);

    UserAccount getCurrentUserAccount();

    boolean isUserConnected();
}
