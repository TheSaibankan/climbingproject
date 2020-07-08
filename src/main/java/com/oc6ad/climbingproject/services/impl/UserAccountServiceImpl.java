package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.RoleRepo;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserAccountServiceImpl extends AbstractService<UserAccount, Long> implements UserAccountService {
    private final Cryptpass cryptpass;
    private final UserAccountRepo userAccountRepo;
    private final RoleRepo roleRepo;

    public UserAccountServiceImpl(Cryptpass cryptpass, UserAccountRepo userAccountRepo, RoleRepo roleRepo) {
        this.cryptpass = cryptpass;
        this.userAccountRepo = userAccountRepo;
        this.roleRepo = roleRepo;
    }


    @Override
    public void addUserAccount(UserAccount userAccount) {
        String generatedSalt = cryptpass.getSalt();
        userAccount.setPassword(cryptpass.encrypt(userAccount.getPassword(), generatedSalt));
        userAccount.setSalt(generatedSalt);
        userAccount.setRoles(Arrays.asList(roleRepo.findByName("ROLE_USER")));
        save(userAccount);
    }

    @Override
    public UserAccount loadUserByLogin(String login) {
        UserAccount userAccount = userAccountRepo.findByLogin(login);
        return userAccount;
    }

    @Override
    public UserAccount getCurrentUserAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userAccountRepo.findByLogin(authentication.getName());
    }

    @Override
    public boolean isUserConnected() {
        return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
    }


    @Override
    protected CrudRepository<UserAccount, Long> getRepository() {
        return this.userAccountRepo;
    }
}
