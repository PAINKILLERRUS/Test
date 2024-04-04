package org.example.websSrvice.model;

import java.util.List;

public class Address {
    private String type;
    private Properties properties;
    private List<String> required;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "Address{" +
                "type='" + type + '\'' +
                ", properties=" + properties +
                ", required=" + required +
                '}';
    }
}
