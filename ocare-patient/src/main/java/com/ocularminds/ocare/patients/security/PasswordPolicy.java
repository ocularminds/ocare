package com.ocularminds.ocare.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordPolicy {

    private static final Pattern P0 = Pattern.compile("\\w+\\.");
    private static final Pattern NW = Pattern.compile("(?=.*[^\\w]).+$");
    private static final Pattern DG = Pattern.compile("(?=.*\\d).+$");
    private static final Pattern UC = Pattern.compile("(?=.*[A-Z]).+$");
    private static final Pattern LC = Pattern.compile("(?=.*[a-z]).+$");

    private static boolean isMatched(Pattern p, String input) {
        Matcher m = p.matcher(input);
        return m.matches();
    }

    public static boolean containsSpecialCharacter(String password) {
        return PasswordPolicy.isMatched(NW, password);
    }

    public static boolean containsUpperCase(String password) {
        return PasswordPolicy.isMatched(UC, password);
    }

    public static boolean containsLowerCase(String password) {
        return PasswordPolicy.isMatched(LC, password);
    }

    public static boolean containsNumber(String password) {
        return PasswordPolicy.isMatched(DG, password);
    }

    public static boolean isAlphaNumeric(String password) {
        return (PasswordPolicy.containsLowerCase(password) || PasswordPolicy
                .containsUpperCase(password)) && PasswordPolicy.containsNumber(password);
    }
}
