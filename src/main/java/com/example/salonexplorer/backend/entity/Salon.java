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
@ToString
public class Salon {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Embedded
    Address address;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "salon_photos")
    List<Photo> photos;

    @Column(columnDefinition = "TEXT")
    String websiteUrl;

    @Column(columnDefinition = "TEXT")
    String gMapsUri;

    String externalId;
    String name;
    String phoneNumber;
    Integer userRatingCount;
    Double rating;
    List<String> types;
}
