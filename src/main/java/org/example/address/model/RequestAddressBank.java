package org.example.address.model;

/**
 * Класс модель(POJO) RequestAddressBank с обьявленными переменными,
 * в которых будет храниться полезная информация для передачи в запросе GET
 */
public class RequestAddressBank {

    private String location;
    private Integer radius;
    private Integer maxResult;
    private String filterTerm;
    private String filterCountry;
    private Integer filterRegion;
    private Integer filterAgent;
    private String filterOperation;
    private String filterCurrency;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Integer getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(Integer maxResult) {
        this.maxResult = maxResult;
    }

    public String getFilterTerm() {
        return filterTerm;
    }

    public void setFilterTerm(String filterTerm) {
        this.filterTerm = filterTerm;
    }

    public String getFilterCountry() {
        return filterCountry;
    }

    public void setFilterCountry(String filterCountry) {
        this.filterCountry = filterCountry;
    }

    public Integer getFilterRegion() {
        return filterRegion;
    }

    public void setFilterRegion(Integer filterRegion) {
        this.filterRegion = filterRegion;
    }

    public Integer getFilterAgent() {
        return filterAgent;
    }

    public void setFilterAgent(Integer filterAgent) {
        this.filterAgent = filterAgent;
    }

    public String getFilterOperation() {
        return filterOperation;
    }

    public void setFilterOperation(String filterOperation) {
        this.filterOperation = filterOperation;
    }

    public String getFilterCurrency() {
        return filterCurrency;
    }

    public void setFilterCurrency(String filterCurrency) {
        this.filterCurrency = filterCurrency;
    }
}
