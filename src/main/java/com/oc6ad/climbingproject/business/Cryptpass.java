package com.oc6ad.climbingproject.business;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class Cryptpass {

    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean checkPass(String clearPassword, String salt, String hashpass) {
        return BCrypt.checkpw(clearPassword, hashpass);
    }

    public String getSalt() {
        return BCrypt.gensalt(10);
    }

    /*public boolean checkPass(UserAccount user, String clearPassword) {
        BCrypt.hashpw(clearPassword, user.getSalt());
        return BCrypt.checkpw(clearPassword, user.getPassword());
    }*/
}
