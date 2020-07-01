package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class UserAccountServiceImpl extends AbstractService<UserAccount, Long> implements UserAccountService {
    @Autowired
    private Cryptpass cryptpass;
    @Autowired
    private UserAccountRepo userAccountRepo;



    @Override
    public void addUserAccount(UserAccount userAccount) {
        String generatedSalt = cryptpass.getSalt();
        userAccount.setPassword(cryptpass.encrypt(userAccount.getPassword(), generatedSalt));
        userAccount.setSalt(generatedSalt);
        save(userAccount);
    }

    @Override
    public UserAccount loadUserByLogin(String login) {
        UserAccount userAccount = userAccountRepo.findByLogin(login);
        return userAccount;
    }

    @Override
    protected CrudRepository<UserAccount, Long> getRepository() {
        return this.userAccountRepo;
    }
}
