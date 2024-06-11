package com.shared.info.pojo;

import lombok.Builder;

@Builder
public class CustomerEntitlements {

    private String id;
    private String domicileCountry;

    public CustomerEntitlements() {
    }

    public CustomerEntitlements(String id, String domicileCountry) {
        this.id = id;
        this.domicileCountry = domicileCountry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomicileCountry() {
        return domicileCountry;
    }

    public void setDomicileCountry(String domicileCountry) {
        this.domicileCountry = domicileCountry;
    }

    @Override
    public String toString() {
        return "CustomerEntitlements{" +
                "id='" + id + '\'' +
                ", domicileCountry='" + domicileCountry + '\'' +
                '}';
    }
}
