package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl extends AbstractService<UserAccount, Long> implements UserAccountService {
    private final Cryptpass cryptpass;

    private final UserAccountRepo userAccountRepo;

    public UserAccountServiceImpl(Cryptpass cryptpass, UserAccountRepo userAccountRepo) {
        this.cryptpass = cryptpass;
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public void addUserAccount(UserAccount userAccount) {
        String generatedSalt = cryptpass.getSalt();
        userAccount.setPassword(cryptpass.encrypt(userAccount.getPassword(), generatedSalt));
        userAccount.setSalt(generatedSalt);
        save(userAccount);
    }

    @Override
    protected CrudRepository<UserAccount, Long> getRepository() {
        return this.userAccountRepo;
    }
}
