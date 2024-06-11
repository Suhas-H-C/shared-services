package com.shared.info.pojo;

import lombok.Builder;

@Builder
public class ClientEntitlement {
    private String id;
    private String domicileCountry;

    public ClientEntitlement() {
    }

    public ClientEntitlement(String id, String domicileCountry) {
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
        return "ClientEntitlement{" +
                "id='" + id + '\'' +
                ", domicileCountry='" + domicileCountry + '\'' +
                '}';
    }
}
