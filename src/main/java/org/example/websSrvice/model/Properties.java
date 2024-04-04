package org.example.websSrvice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Properties {
    @JsonProperty("FirstName")
    private FirstName firstName;
    @JsonProperty("LastName")
    private LastName lastName;
    @JsonProperty("MiddleName")
    private MiddleName middleName;
    @JsonProperty("PhoneNumber")
    private PhoneNumber phoneNumber;
    @JsonProperty("Gender")
    private Gender gender;
    @JsonProperty("BirthDate")
    private BirthDate birthDate;
    @JsonProperty("BirthPlace")
    private BirthPlace birthPlace;
    @JsonProperty("CountryOfResidence")
    private CountryOfResidence countryOfResidence;
    @JsonProperty("Address")
    private Address address;
    @JsonProperty("CountryCode")
    private CountryCode countryCode;
    @JsonProperty("PostCode")
    private PostCode postCode;
    @JsonProperty("State")
    private State state;
    @JsonProperty("City")
    private City city;
    @JsonProperty("District")
    private District district;
    @JsonProperty("Street")
    private Street street;
    @JsonProperty("House")
    private House house;
    @JsonProperty("Building")
    private Building building;
    @JsonProperty("Apartment")
    private Apartment apartment;

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    public MiddleName getMiddleName() {
        return middleName;
    }

    public void setMiddleName(MiddleName middleName) {
        this.middleName = middleName;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    public BirthPlace getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(BirthPlace birthPlace) {
        this.birthPlace = birthPlace;
    }

    public CountryOfResidence getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(CountryOfResidence countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CountryCode getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(CountryCode countryCode) {
        this.countryCode = countryCode;
    }

    public PostCode getPostCode() {
        return postCode;
    }

    public void setPostCode(PostCode postCode) {
        this.postCode = postCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "firstName=" + firstName +
                ", lastName=" + lastName +
                ", middleName=" + middleName +
                ", phoneNumber=" + phoneNumber +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
                ", birthPlace=" + birthPlace +
                ", countryOfResidence=" + countryOfResidence +
                ", address=" + address +
                ", countryCode=" + countryCode +
                ", postCode=" + postCode +
                ", state=" + state +
                ", city=" + city +
                ", district=" + district +
                ", street=" + street +
                ", house=" + house +
                ", building=" + building +
                ", apartment=" + apartment +
                '}';
    }
}
