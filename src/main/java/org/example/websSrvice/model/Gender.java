package org.example.websSrvice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Gender {
    @JsonProperty("enum")
    private List<String> myEnum;

    public List<String> getMyEnum() {
        return myEnum;
    }

    public void setMyEnum(List<String> myEnum) {
        this.myEnum = myEnum;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "myEnum=" + myEnum +
                '}';
    }
}
