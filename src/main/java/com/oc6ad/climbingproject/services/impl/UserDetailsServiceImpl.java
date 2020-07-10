package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserAccountRepo userAccountRepo;

    public UserDetailsServiceImpl(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepo.findByLogin(login);
        if(userAccount==null)
            throw new UsernameNotFoundException("User not found !");
        return new UserPrincipal((userAccount));
    }
}
