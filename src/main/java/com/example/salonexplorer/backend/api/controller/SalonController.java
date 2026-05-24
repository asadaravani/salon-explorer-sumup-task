package com.example.salonexplorer.backend.api.controller;

import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import com.example.salonexplorer.backend.service.SalonService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/salons")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SalonController {
    SalonService service;

    @GetMapping
    public Page<SalonPreviewDto> getAllSalons(
            @RequestParam(required = false) String district,
            @RequestParam(required = false) String search,
            @RequestParam(required = false, defaultValue = "4.9") Double minRating,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size
    ) {
        return service.getAllPreview(district, search, minRating, PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    public SalonDetailedDto getSalonById(@PathVariable Long id) {
        return service.getByIdDetailed(id);
    }
}
