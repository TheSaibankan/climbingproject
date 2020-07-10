package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.Role;
import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.RoleRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private UserAccount userAccount;

    public UserPrincipal(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userAccount.isAdmin()){
            return Collections.singleton(new SimpleGrantedAuthority("ADMIN"));
        }
        else {
            return Collections.singleton(new SimpleGrantedAuthority("USER"));
        }
    }

    @Override
    public String getPassword() {
        return userAccount.getPassword();
    }

    @Override
    public String getUsername() {
        return userAccount.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
