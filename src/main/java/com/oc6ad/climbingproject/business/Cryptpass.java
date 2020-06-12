package com.oc6ad.climbingproject.business;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class Cryptpass {

    private String hashpass;
    private boolean verifPass;

    public String encrypt(String password) {
        hashpass = BCrypt.hashpw(password, BCrypt.gensalt(10));
        return hashpass;
    }

    public boolean checkPass(String password) {
        if (BCrypt.checkpw(password, hashpass)) {
            verifPass = true;
        } else {
            verifPass = false;
        }
        return verifPass;
    }
}
