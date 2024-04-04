package org.example.address.model;

import java.util.List;

/**
 * класс модель(POJO) ResponseAddressBank c объявленными переменными,
 * которые будут хранить десериализованную информацию из JSON
 */

public class ResponseAddressBank {
    private Integer totalCount;
    private List<Item> items;
    private Link _links;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Link get_links() {
        return _links;
    }

    public void set_links(Link _links) {
        this._links = _links;
    }
}
