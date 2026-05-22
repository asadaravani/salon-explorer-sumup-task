package com.example.salonexplorer.backend.mapper;

import com.example.salonexplorer.backend.client.dto.GooglePlaceDto;
import com.example.salonexplorer.backend.client.dto.GooglePlacesResponseDto;
import com.example.salonexplorer.backend.entity.Salon;
import java.util.ArrayList;
import java.util.List;

public class GooglePlaceMapper {
    public List<Salon> toEntities(GooglePlacesResponseDto dto) {
        List<Salon> salons = new ArrayList<>();
        dto.places().forEach(place -> {
            if (place != null)
                salons.add(toEntity(place));
        });
        return salons;
    }
    private Salon toEntity(GooglePlaceDto dto) {
        Salon salon = new Salon();
        salon.setExternalId(dto.id());
        salon.setName(dto.displayName().text());
        salon.setPhoneNumber(dto.nationalPhoneNumber());
        salon.setWebsite(dto.websiteUri());
        salon.setGMapsUri(dto.googleMapsUri());
        salon.setUserRatingCount(dto.userRatingCount());
        salon.setRating(dto.rating());
        salon.setAddress(dto.formattedAddress());
        salon.setTypes(filterTypes(dto.types()));
        return salon;
    }
    private List<String> filterTypes(List<String> types) {
        List<String> unwanted = List.of(
                "service",
                "point_of_interest",
                "establishment"
        );
        return types.stream()
                .filter(type -> !unwanted.contains(type))
                .toList();
    }
}
