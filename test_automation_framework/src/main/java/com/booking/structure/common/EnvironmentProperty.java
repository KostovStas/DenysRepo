package com.booking.structure.common;

public enum EnvironmentProperty {

    BOOKING_SITE_STORIES,
    SITE_BASE_URL;

    public String readProperty() {
        return PropertiesController.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
