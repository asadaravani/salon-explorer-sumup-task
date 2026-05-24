package com.example.salonexplorer.backend.service;

import com.example.salonexplorer.backend.api.dto.DistrictDto;
import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SalonService {
    SalonDetailedDto getByIdDetailed(Long id);

    Page<SalonPreviewDto> getAllPreview(String district, String search, Double minRating, Pageable pageable);

    List<DistrictDto> findDistrictStats();
}
