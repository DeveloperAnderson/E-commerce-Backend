package com.commerce.config.contantes.profile;

import java.util.Map;

public class ProfileInfo {
    private String activeProfiles;
    private Map<String, String> systemProperties;
    private Map<String, String> systemEnvironment;

    // Getters and setters



    public String getActiveProfiles() {
        return activeProfiles;
    }

    public void setActiveProfiles(String activeProfiles) {
        this.activeProfiles = activeProfiles;
    }

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Map<String, String> getSystemEnvironment() {
        return systemEnvironment;
    }

    public void setSystemEnvironment(Map<String, String> systemEnvironment) {
        this.systemEnvironment = systemEnvironment;
    }
}