package com.example.salonexplorer.backend.api.dto;

public record SalonPreviewDto(
        Long id,
        String name,
        String district,
        Double rating,
        String photoUrl
) {
}
