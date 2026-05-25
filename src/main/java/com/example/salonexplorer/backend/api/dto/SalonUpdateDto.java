package com.example.salonexplorer.backend.api.dto;

import java.util.List;

public record SalonUpdateDto(
        String name,
        String district,
        String phoneNumber,
        String websiteUrl,
        String googleMapsUrl,
        Integer userRatingCount,
        Double rating,
        List<String> types,
        List<String> photosUrlsToRemove
) {
}
