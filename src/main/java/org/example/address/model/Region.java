package org.example.address.model;

import java.util.List;

public class Region {
    private Integer id;
    private String countryCode;
    private String name;
    private List<String> currencies;
    private Boolean subway;
    private List<String> branches;
    private Link _links;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public Boolean getSubway() {
        return subway;
    }

    public void setSubway(Boolean subway) {
        this.subway = subway;
    }

    public List<String> getBranches() {
        return branches;
    }

    public void setBranches(List<String> branches) {
        this.branches = branches;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }
}
