package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import com.oc6ad.climbingproject.services.AbstractService;
import com.oc6ad.climbingproject.services.UserAccountService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    @Override
    protected CrudRepository<UserAccount, Long> getRepository() {
        return this.userAccountRepo;
    }
}
