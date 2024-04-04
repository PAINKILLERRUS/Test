package org.example.address.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private Boolean isHidden;
    private Integer id;
    private Integer agentId;
    private String name;
    private String shortName;
    private String address;
    private String addressNotes;
    private Location location;
    private String phone;
    private Country country;
    private Region region;
    private WorkingSchedule workingSchedule;
    private List<Metro> metro;
    private List<String> currencies;
    private List<Operation> operations;
    private String notes;
    private String activity;
    private Link _links;

    public Boolean getHidden() {
        return isHidden;
    }

    public void setHidden(Boolean hidden) {
        isHidden = hidden;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressNotes() {
        return addressNotes;
    }

    public void setAddressNotes(String addressNotes) {
        this.addressNotes = addressNotes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public WorkingSchedule getWorkingSchedule() {
        return workingSchedule;
    }

    public void setWorkingSchedule(WorkingSchedule workingSchedule) {
        this.workingSchedule = workingSchedule;
    }

    public List<Metro> getMetro() {
        return metro;
    }

    public void setMetro(List<Metro> metro) {
        this.metro = metro;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }
}
