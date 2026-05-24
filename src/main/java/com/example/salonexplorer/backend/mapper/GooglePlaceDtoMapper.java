package com.example.salonexplorer.backend.mapper;

import com.example.salonexplorer.backend.client.dto.AddressComponentDto;
import com.example.salonexplorer.backend.client.dto.GooglePlaceDto;
import com.example.salonexplorer.backend.client.dto.GooglePlacePhotoDto;
import com.example.salonexplorer.backend.entity.Address;
import com.example.salonexplorer.backend.entity.Photo;
import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class GooglePlaceDtoMapper {
    public List<Salon> toEntities(List<GooglePlaceDto> list) {
        List<Salon> salons = new ArrayList<>();
        list.forEach(place -> {
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
        salon.setWebsiteUrl(dto.websiteUri());
        salon.setGMapsUri(dto.googleMapsUri());
        salon.setUserRatingCount(dto.userRatingCount());
        salon.setRating(dto.rating());
        salon.setTypes(filterTypes(dto.types()));
        salon.setPhotos(mapPhotoDtoToEntity(dto.photos()));
        salon.setAddress(mapAddressComponentsToEntity(dto.addressComponents()));
        return salon;
    }
    private Address mapAddressComponentsToEntity(List<AddressComponentDto> list) {
        if (list == null || list.isEmpty())
            return null;

        Address address = new Address();

        for (AddressComponentDto dto : list) {
            if (dto == null || dto.types() == null)
                continue;

            if (dto.types().contains("route"))
                address.setStreet(dto.longText());

            else if (dto.types().contains("street_number"))
                address.setStreetNumber(dto.longText());

            else if (dto.types().contains("sublocality"))
                address.setDistrict(dto.longText());

            else if (dto.types().contains("postal_code"))
                address.setPostalCode(dto.longText());
        }
        return address;
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

    private List<Photo> mapPhotoDtoToEntity(List<GooglePlacePhotoDto> list) {
        if (list == null || list.isEmpty())
            return null;

        List<Photo> photos = new ArrayList<>();
        for (GooglePlacePhotoDto dto : list) {
            if (dto == null)
                continue;

            Photo photo = new Photo();
            photo.setName(dto.name());
            photo.setWidth(dto.widthPx());
            photo.setHeight(dto.heightPx());
            photos.add(photo);
        }
        return photos;
    }
}
