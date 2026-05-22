package com.example.salonexplorer.backend.api.dto;

public record SalonPreviewDto(
        Long id,
        String name,
        String street,
        String district,
        Double rating
) {
}
