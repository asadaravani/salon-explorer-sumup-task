package com.example.salonexplorer.backend.mapper;

import com.example.salonexplorer.backend.api.dto.*;
import com.example.salonexplorer.backend.entity.Address;
import com.example.salonexplorer.backend.entity.Photo;
import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SalonDtoMapper {
    @Value("${google.api}")
    private String apiKey;
    private final String PHOTO_BASE_URL = "https://places.googleapis.com/v1/";


    public SalonDetailedDto mapToDto(Salon salon) {
        return new SalonDetailedDto(
                salon.getId(),
                salon.getName(),
                mapToAddressDto(salon.getAddress()),
                salon.getPhoneNumber(),
                salon.getWebsiteUrl(),
                salon.getGMapsUri(),
                salon.getUserRatingCount(),
                salon.getRating(),
                salon.getTypes(),
                mapToPhotoDtoList(salon.getPhotos())
        );
    }

    public SalonPreviewDto mapToPreviewDto(Salon salon) {
        return new SalonPreviewDto(
                salon.getId(),
                salon.getName(),
                salon.getAddress().getDistrict(),
                salon.getRating(),
                generatePreviewPhoto(salon.getPhotos().get(0))
        );
    }

    private String generatePreviewPhoto(Photo photo) {
        if (photo == null || photo.getName() == null)
            return null;
        return
                PHOTO_BASE_URL + photo.getName() +
                        "/media?maxWidthPx=300&key=" + apiKey;
    }
    private AddressDto mapToAddressDto(Address address) {
        return new AddressDto(
                address.getStreet().concat(" ").concat(address.getStreetNumber()),
                address.getDistrict(),
                address.getPostalCode()
        );
    }
    private List<PhotoDto> mapToPhotoDtoList(List<Photo> photos) {
        if (photos == null || photos.isEmpty())
            return null;

        List<PhotoDto> list = new ArrayList<>();
        for (Photo photo : photos) {
            if (photo == null || photo.getName() == null)
                continue;
            String url = PHOTO_BASE_URL
                    + photo.getName()
                    + "/media?maxWidthPx="
                    + photo.getWidth()
                    + "&key=" + apiKey;
            list.add(new PhotoDto(url, photo.getWidth(), photo.getHeight()));
        }
        return list;
    }
}
