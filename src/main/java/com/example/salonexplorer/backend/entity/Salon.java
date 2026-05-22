package com.example.salonexplorer.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Salon {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String externalId;
    String name;
    String phoneNumber;
    String website;
    String gMapsUri;
    Integer userRatingCount;
    Double rating;
    String address;
    List<String> types;

}
