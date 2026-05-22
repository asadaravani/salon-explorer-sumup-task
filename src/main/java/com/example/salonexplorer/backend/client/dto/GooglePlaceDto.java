package com.example.salonexplorer.backend.client.dto;

import java.util.List;

public record GooglePlaceDto(

        String id,

        List<String> types,

        String nationalPhoneNumber,

        String formattedAddress,

        Double rating,

        String googleMapsUri,

        String websiteUri,

        Integer userRatingCount,

        DisplayNameDto displayName

) {
}
