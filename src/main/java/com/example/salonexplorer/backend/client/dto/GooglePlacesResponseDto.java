package com.example.salonexplorer.backend.client.dto;

import java.util.List;

public record GooglePlacesResponseDto(
        List<GooglePlaceDto> places
) {
}
