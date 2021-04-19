package com.example.fitnessapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordManager {

    private static boolean checkPassword(String password) {
        /* Checks if the password abides the rules of a good password*/
        boolean overTwelve = false;
        boolean hasCapital = false;
        boolean hasLower = false;
        boolean hasNumb = false;
        boolean hasSpecial = false;
        char ch;
        Pattern special= Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = special.matcher(password);
        boolean hasSymbols = matcher.find();
        if (password.length()< 12){
            overTwelve = true;
        }
        for (int i = 0; i < password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                hasNumb = true;
            } else if (Character.isUpperCase(ch)) {
                hasCapital = true;
            } else if (Character.isLowerCase(ch)) {
                hasLower = true;
            } else if(hasSymbols == true){
                hasSpecial = true;
            }
            if (overTwelve && hasNumb && hasCapital && hasLower && hasSpecial)
                return true;
        }
        return false;
    }

    /*Password is hash protected which return an integer*/
    private int hashPassword(String password){
        int hash = password.hashCode();
        return hash;
    }

    /*Compares the given password to the users hash protected password to see if the integers match*/
    /*private boolean checkPassword(String username, String password) {
        //TODO PULL THE USERS HASH INTEGER FROM DATABASE
        if(password.hashCode() == USERS INTEGER HERE){
            return true;
        }
        return false;
    }*/
}
