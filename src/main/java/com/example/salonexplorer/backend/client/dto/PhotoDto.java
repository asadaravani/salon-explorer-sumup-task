package com.example.salonexplorer.backend.client.dto;

import java.util.List;

public record PhotoDto(

        String name,

        Integer widthPx,

        Integer heightPx,

        List<AuthorAttributionDto> authorAttributions,

        String googleMapsUri

) {}
