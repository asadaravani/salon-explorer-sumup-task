package com.example.salonexplorer.backend.api.dto;

public record AddressDto(
        String streetAndNo,
        String district,
        String postalCode
) {
}
