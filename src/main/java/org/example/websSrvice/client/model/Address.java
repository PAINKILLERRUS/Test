package org.example.websSrvice.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс модель(POJO) Address с обьявленными переменными,
 * в которых будет храниться полезная информация полученная от клиента
 */
public class Address {

    @JsonProperty("CountryCode")
    private String CountryCode;
    @JsonProperty("PostCode")
    private String PostCode;
    @JsonProperty("State")
    private String State;
    @JsonProperty("City")
    private String City;
    @JsonProperty("District")
    private String District;
    @JsonProperty("Street")
    private String Street;
    @JsonProperty("House")
    private String House;
    @JsonProperty("Building")
    private String Building;
    @JsonProperty("Apartment")
    private String Apartment;

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getHouse() {
        return House;
    }

    public void setHouse(String house) {
        House = house;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getApartment() {
        return Apartment;
    }

    public void setApartment(String apartment) {
        Apartment = apartment;
    }

    @Override
    public String toString() {
        return "Address{" +
                "CountryCode='" + CountryCode + '\'' +
                ", PostCode='" + PostCode + '\'' +
                ", State='" + State + '\'' +
                ", City='" + City + '\'' +
                ", District='" + District + '\'' +
                ", Street='" + Street + '\'' +
                ", House='" + House + '\'' +
                ", Building='" + Building + '\'' +
                ", Apartment='" + Apartment + '\'' +
                '}';
    }
}
