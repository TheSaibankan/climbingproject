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


    /**
     * Add and save a new UserAccount
     * @param userAccount
     */
    @Override
    public void addUserAccount(UserAccount userAccount) {
        String generatedSalt = cryptpass.getSalt();
        userAccount.setPassword(cryptpass.encrypt(userAccount.getPassword(), generatedSalt));
        save(userAccount);
    }

    /**
     * Verify the existence of an account via its login
     * @param login
     * @return Boolean
     */
    @Override
    public Boolean existsByLogin(String login) {
        return userAccountRepo.existsByLogin(login);
    }

    /**
     * Verify the existence of an user via the login
     * @param login
     * @return UserAccount
     */
    @Override
    public UserAccount findByLogin(String login) {
        return userAccountRepo.findByLogin(login);
    }

    /**
     * Get the actual user by using SecurityContextHolder
     * @return UserAccount
     */
    @Override
    public UserAccount getCurrentUserAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userAccountRepo.findByLogin(authentication.getName());
    }

    /**
     * Is used to know if the current user is authenticated (not anonymous)
     * @return boolean
     */
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
