package com.example.salonexplorer.backend.mapper;

import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import com.example.salonexplorer.backend.entity.Salon;
import java.util.ArrayList;
import java.util.List;

public class SalonDtoMapper {
    public List<SalonPreviewDto> mapToDtoAll(List<Salon> list) {
        List<SalonPreviewDto> dtos = new ArrayList<>();
        return dtos;
    }

    public SalonDetailedDto mapToDto(Salon salon) {
        SalonDetailedDto dto = new SalonDetailedDto();
        return dto;
    }

    private SalonPreviewDto mapToPreviewDto(Salon salon) {
        return new SalonPreviewDto(
                salon.getId(),
                salon.getName(),
                sa
        )
    }
}
