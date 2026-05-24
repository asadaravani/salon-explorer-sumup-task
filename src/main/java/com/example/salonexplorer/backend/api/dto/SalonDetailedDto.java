package com.example.salonexplorer.backend.api.dto;

import java.util.List;

public record SalonDetailedDto(
        Long id,
        String name,
        AddressDto address,
        String phoneNumber,
        String websiteUrl,
        String googleMapsUrl,
        Integer userRatingCount,
        Double rating,
        List<String> types_services,
        List<PhotoDto> photos
) {
}
