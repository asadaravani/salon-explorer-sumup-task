package com.example.salonexplorer.backend.client.dto;

import java.util.List;

public record AddressComponentDto(
        String longText,
        String shortText,
        List<String> types,
        String languageCode
) {}
