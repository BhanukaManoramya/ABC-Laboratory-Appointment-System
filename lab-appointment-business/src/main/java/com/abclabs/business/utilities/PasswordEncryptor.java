package com.abclabs.business.utilities;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    public static String encryptPassword(String  passwordToEncrypt) {
    	String securedPasswordHash = BCrypt.hashpw(passwordToEncrypt, BCrypt.gensalt(12));
    	boolean isMatched = BCrypt.checkpw(passwordToEncrypt, securedPasswordHash);
    	if (isMatched) {
			return securedPasswordHash;
		}
    	return null;
    }
}
