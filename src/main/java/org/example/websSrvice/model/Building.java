package org.example.websSrvice.model;

public class Building {
    private String type;
    private Integer maxLength;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String toString() {
        return "Building{" +
                "type='" + type + '\'' +
                ", maxLength=" + maxLength +
                '}';
    }
}
