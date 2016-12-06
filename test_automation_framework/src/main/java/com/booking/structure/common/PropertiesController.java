package com.booking.structure.common;

import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.booking.structure.logging.Logger;

public final class PropertiesController {

    private static PropertiesController instance;
    private final Properties properties = new Properties();
    private static final String PROPERTY_FILE_PATH_DEFAULT = "src/main/resources/properties/";
    private static final String ENV_CONFIG_FILE = "env.config.file";
    private static final String PROPERTY_TEMPLATE = "\\$\\{([a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*+)\\}";

    private PropertiesController() {
        final String envConfigFile = System.getProperty(ENV_CONFIG_FILE);
        final String path = PROPERTY_FILE_PATH_DEFAULT + envConfigFile;
        loadProperties(path);
        properties.put(ENV_CONFIG_FILE, path);
    }

    private static PropertiesController getInstance() {
        if (instance == null) {
            instance = new PropertiesController();
        }
        return instance;
    }

    public static String getProperty(final String propertyName) {
        String propertyValue = System.getProperty(propertyName, getInstance().properties.getProperty(propertyName));
        if (nonNull(propertyValue) && Pattern.matches(PROPERTY_TEMPLATE, propertyValue)) {
            final Matcher m = Pattern.compile(PROPERTY_TEMPLATE).matcher(propertyValue);
            while (m.find()) {
                final String valueToReplace = m.group(1);
                propertyValue = replaceOnce(propertyValue, m.group(0), getProperty(valueToReplace));
            }
        }
        return propertyValue;
    }

    private void loadProperties(final String path) {
        final File file = new File(path);
        final Properties envProperties = new Properties();
        try {
            final FileInputStream propFile = new FileInputStream(file);
            envProperties.load(propFile);
            for (final Object property : envProperties.keySet()) {
                final String key = (String) property;
                if (key.startsWith("+")) {
                    final String incpath = file.getParent() + "/" + trim(substring(key, 1));
                    if (new File(incpath).exists()) {
                        loadProperties(incpath);
                    } else {
                        Logger.out.warn("Cannot find file: {}", incpath);
                    }
                }
            }
            properties.putAll(envProperties);
        } catch (final IOException e) {
            Logger.out.error("Can't load properties", e);
        }
    }
}