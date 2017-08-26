package org.graviton.configuration;

import org.graviton.user.User;

public class Configuration {

    private final static String databaseUsername = "test";
    private final static String databasePassword = "pass";
    private final static Integer databasePort = 3306;
    private final static Boolean test = true;
    private final static char character = 'o';
    private final static User mainUser = new User("user123", "password123", 11);


    public static String getDatabaseUsername() {
        return databaseUsername;
    }

    public static String getDatabasePassword() {
        return databasePassword;
    }

    public static Integer getDatabasePort() {
        return databasePort;
    }

    public static Boolean getTest() {
        return test;
    }

    public static char getCharacter() {
        return character;
    }
}