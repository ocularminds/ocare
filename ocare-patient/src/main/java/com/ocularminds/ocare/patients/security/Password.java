package com.ocularminds.ocare.security;

/*
 * Copyright (c) 2016-2017 Ocular-Minds Software Limited
 * 
 * Permission is hereby granted to Vatebra Limited, to any person representing Vatebra in a copy of
 * this software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to
 * whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY FOR 6-MONTH SUPPORT AFTER INITIAL HANDING OVER
 * OF THE SOURCE CODES
 */
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Password {

    private final Pattern p0 = Pattern.compile("\\w+\\.");
    private final Pattern SPECIA_CHA = Pattern.compile("(?=.*[^\\w]).+$");
    private final Pattern WITH_DIGIT = Pattern.compile("(?=.*\\d).+$");
    private final Pattern UPPER_CASE = Pattern.compile("(?=.*[A-Z]).+$");
    private final Pattern LOWER_CASE = Pattern.compile("(?=.*[a-z]).+$");
    private final String password;

    public Password(String pass) {
        this.password = pass;
    }

    public static String next() {
        String[] dico = {
            "ajZv", "bkQk", "cOvW", "dqXx", "eQqm", "fjZv", "gkQk", "hOvW", "iqXx", "jQqm"
        };
        int r = (int) (Math.random() * dico.length);
        return dico[r] + words[random.nextInt(words.length)];
    }

    public static String next(int minimun) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < minimun; i++) {
            sb.append(characters[random.nextInt(characters.length)]);
        }
        return sb.toString();
    }

    public boolean hasSpecialCharacter() {
        return matched(SPECIA_CHA, this.password);
    }

    public boolean hasUpperCase() {
        return matched(UPPER_CASE, this.password);
    }

    public boolean hasLowerCase() {
        return matched(LOWER_CASE, this.password);
    }

    public boolean hasNumber() {
        return matched(WITH_DIGIT, this.password);
    }

    public boolean isAlphaNumeric() {
        return (hasLowerCase() || hasUpperCase()) && hasNumber();
    }
    
    private boolean matched(Pattern p, String input) {
        Matcher m = p.matcher(input);
        return m.matches();
    }

    protected static Random random = new Random();
    protected static char characters[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'm',
        'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E',
        'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
        'Z', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-', '@', '#'};
    protected static String words[] = {"ghetto", "abc123", "Sopetie12", "maggot", "vested",
        "domino", "zebra", "jackson", "5ohms", "decibel", "dimple", "bet4all", "ketibe",
        "naples", "hephziba", "optimal", "pianissimo", "proclain", "dengide"};

}
