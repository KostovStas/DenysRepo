package com.booking.structure.common;

public enum EnvironmentProperty {

    BOOKING_STORIES;

    public String readProperty() {
        return PropertiesController.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
