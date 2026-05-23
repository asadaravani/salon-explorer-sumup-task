package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import com.example.salonexplorer.backend.client.GooglePlacesClient;
import com.example.salonexplorer.backend.entity.Salon;
import com.example.salonexplorer.backend.repository.SalonRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SalonExportService {
    SalonImportService importService;
    SalonRepository repository;

    public SalonDetailedDto getByIdDetailed(Long id){
        Optional<Salon> salon = Optional.of(repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found")));

    }

    public List<SalonPreviewDto> getAllPreview(){
        List<Salon> salons = repository.findAll();
        if(salons.isEmpty()){
            throw new EntityNotFoundException("Salon not found");
        }
    }

    public void updateDataBase(){
        List<Salon> salons = importService.importAll();
        if(salons == null || salons.isEmpty()){
            throw new IllegalStateException("Api returned empty list");
        }
        repository.saveAll(salons);
    }

}
