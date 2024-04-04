package org.example.websSrvice.model;

public class BirthDate {
    private String type;
    private String format;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "BirthDate{" +
                "type='" + type + '\'' +
                ", format='" + format + '\'' +
                '}';
    }
}
