package com.commerce.config.contantes.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProfileController {

    @Autowired
    private Environment env;

    @GetMapping("/profile")
    public ProfileInfo getActiveProfile() {
        String[] activeProfiles = env.getActiveProfiles();
        String activeProfilesStr = String.join(", ", activeProfiles);

        Map<String, String> systemProperties = System.getenv();
        Map<String, String> systemEnvironment = System.getenv();
        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setActiveProfiles(activeProfilesStr);
        profileInfo.setSystemProperties(systemProperties);
        profileInfo.setSystemEnvironment(systemEnvironment);

        return profileInfo;
    }
}