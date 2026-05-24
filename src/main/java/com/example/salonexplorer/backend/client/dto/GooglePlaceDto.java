package com.example.salonexplorer.backend.client.dto;

import java.util.List;

public record GooglePlaceDto(

        String id,

        List<String> types,

        String nationalPhoneNumber,

        List<AddressComponentDto> addressComponents,

        Double rating,

        String googleMapsUri,

        String websiteUri,

        Integer userRatingCount,

        DisplayNameDto displayName,

        List<GooglePlacePhotoDto> photos
) {
}
