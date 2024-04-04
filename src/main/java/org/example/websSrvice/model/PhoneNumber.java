package org.example.websSrvice.model;

public class PhoneNumber {
    private String type;
    private Integer minLength;
    private Integer maxLength;
    private String pattern;

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

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "type='" + type + '\'' +
                ", minLength=" + minLength +
                ", maxLength=" + maxLength +
                ", pattern='" + pattern + '\'' +
                '}';
    }
}
