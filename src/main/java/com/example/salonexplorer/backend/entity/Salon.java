package com.example.salonexplorer.backend.entity;

import jakarta.persistence.*;
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

    @Embedded
    Address address;

    String externalId;
    String name;
    String phoneNumber;
    String website;
    String gMapsUri;
    Integer userRatingCount;
    Double rating;
    List<String> types;
    List<String> photoIds;
}
