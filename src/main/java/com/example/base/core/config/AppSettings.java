package com.example.base.core.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

@Slf4j
public class AppSettings {

    private static String CONFIG_FILE_NAME;
    private static final Logger LOGGER = Logger.getLogger(AppSettings.class.getName());
    public static AppSettings Instance = null;

    static {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String env = System.getProperty("env");
        try {
            if (env.equals("qa")) {
                CONFIG_FILE_NAME = "config.yml";
            } else if (env.equals("auto")) {
                CONFIG_FILE_NAME = "config-auto.yml";
            } else {
                CONFIG_FILE_NAME = "config-qa.yml";
            }
        } catch (NullPointerException e) {
            CONFIG_FILE_NAME = "config.yml";
        }
        try {
            Instance = mapper.readValue(getConfigFile(), AppSettings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File getConfigFile() {
        File configFile = new File(CONFIG_FILE_NAME);
        LOGGER.info(CONFIG_FILE_NAME);
        if (!configFile.exists()) {
            LOGGER.info("Environment config file does not exist");
            try {
                configFile = new File("CONFIG_FILE_NAME");
            } catch (NullPointerException e) {
                configFile = new File("CONFIG_FILE_NAME");
            }
        }
        return configFile;
    }

    @JsonProperty
    public ApiConfig petstoreApi;

    // region Login
    @JsonProperty
    public String loginUrlWithAppKey;

    @JsonProperty
    public String loginUrl;

}