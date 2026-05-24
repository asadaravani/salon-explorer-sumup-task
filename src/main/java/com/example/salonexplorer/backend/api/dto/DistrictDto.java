package com.example.salonexplorer.backend.api.dto;

public record DistrictDto(
        String name,
        Long availableSalonsCount
) {
}
