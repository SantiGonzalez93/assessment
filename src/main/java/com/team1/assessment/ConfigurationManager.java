package com.team1.assessment;


public class ConfigurationManager {

    private static volatile ConfigurationManager instance;


    private final int maxFileSize;

    private ConfigurationManager() {
        this.maxFileSize = 5242880;
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }


}
