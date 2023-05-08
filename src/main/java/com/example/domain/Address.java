package com.example.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    public Address(String zipCode, String street, String detail) {
        this.zipCode = zipCode;
        this.street = street;
        this.detail = detail;
    }

    private String zipCode;

    private String street;

    private String detail;
}
