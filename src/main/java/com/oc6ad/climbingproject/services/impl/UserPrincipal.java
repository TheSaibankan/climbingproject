package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * This class uses UserDetails in order to configure Spring Security
 */
public class UserPrincipal implements UserDetails {

    private final UserAccount userAccount;

    public UserPrincipal(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * Authorities are determined via the role of the user
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userAccount.getRole()));
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
