package org.example.websSrvice.model;

import java.util.List;

/**
 * Класс модель(POJO) Root с обьявленными переменными,
 * которые будут хранить десериализованную информацию из JSON
 */
public class Root {
    private String id;
    private Properties properties;
    private List<String> required;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Root{" +
                "id='" + id + '\'' +
                ", properties=" + properties +
                ", required=" + required +
                '}';
    }
}
