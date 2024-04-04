package org.example.websSrvice.model;

public class BirthPlace {
    private String type;
    private Integer minLength;
    private Integer maxLength;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    @Override
    public String toString() {
        return "BirthPlace{" +
                "type='" + type + '\'' +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                '}';
    }
}
