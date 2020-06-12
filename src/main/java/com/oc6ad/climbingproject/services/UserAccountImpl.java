package com.oc6ad.climbingproject.services;

import com.oc6ad.climbingproject.model.UserAccount;
import com.oc6ad.climbingproject.repositories.UserAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserAccountImpl implements UserAccountServ {

    private UserAccountRepo userAccountRepo;

    @Autowired
    public UserAccountImpl(UserAccountRepo userAccountRepo) {
        this.userAccountRepo = userAccountRepo;
    }

    @Override
    public List<UserAccount> listAllUsers() {
        List<UserAccount> userAccounts = new ArrayList<>();
        userAccountRepo.findAll().forEach(userAccounts::add);
        return userAccounts;
    }

    @Override
    public Optional<UserAccount> findWithId(Long id) {
        return userAccountRepo.findById(id);
    }

    @Override
    public UserAccount updateAndSave(UserAccount userAccount) {
        userAccountRepo.save(userAccount);
        return userAccount;
    }

    @Override
    public void delete(Long id) {
        userAccountRepo.deleteById(id);
    }

    @Override
    public void deleteAll() {
        userAccountRepo.deleteAll();
    }
}
