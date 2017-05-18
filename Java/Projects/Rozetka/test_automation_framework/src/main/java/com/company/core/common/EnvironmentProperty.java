package com.company.core.common;

public enum EnvironmentProperty {

    ROZETKA_STORIES,
    BASE_SITE_URL,
    BASE_MAIL_API_URL;

    public String readProperty() {
        return PropertiesController.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
