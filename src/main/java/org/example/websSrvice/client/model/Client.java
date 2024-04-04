package org.example.websSrvice.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс модель(POJO) Client с обьявленными переменными,
 * в которых будет храниться полезная информация полученная от клиента
 */
public class Client {

    @JsonProperty("FirstName")
    private String FirstName;
    @JsonProperty("LastName")
    private String LastName;
    @JsonProperty("MiddleName")
    private String MiddleName;
    @JsonProperty("PhoneNumber")
    private String PhoneNumber;
    @JsonProperty("Gender")
    private String Gender;
    @JsonProperty("BirthDate")
    private String BirthDate;
    @JsonProperty("BirthPlace")
    private String BirthPlace;
    @JsonProperty("CountryOfResidence")
    private String CountryOfResidence;
    @JsonProperty("Address")
    private Address Address;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }

    public String getCountryOfResidence() {
        return CountryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        CountryOfResidence = countryOfResidence;
    }

    public Address getAddress() {
        return Address;
    }

    public void setAddress(Address address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", MiddleName='" + MiddleName + '\'' +
                ", PhoneNumber='" + PhoneNumber + '\'' +
                ", Gender='" + Gender + '\'' +
                ", BirthDate='" + BirthDate + '\'' +
                ", BirthPlace='" + BirthPlace + '\'' +
                ", CountryOfResidence='" + CountryOfResidence + '\'' +
                ", Address=" + Address +
                '}';
    }
}
