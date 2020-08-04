package com.oc6ad.climbingproject.services.impl;

import com.oc6ad.climbingproject.model.UserAccount;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

/**
 * Old password encryptor class, is not used to provide security anymore
 * Not deleted in order to test BCrypt later
 */
@Service
public class Cryptpass {

    public String encrypt(String password, String generatedSalt) {
        return BCrypt.hashpw(password, generatedSalt);
    }

    public String getSalt() {
        return BCrypt.gensalt(10);
    }

}
