package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.api.dto.DistrictDto;
import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import com.example.salonexplorer.backend.entity.Salon;
import com.example.salonexplorer.backend.mapper.SalonDtoMapper;
import com.example.salonexplorer.backend.repository.SalonRepository;
import com.example.salonexplorer.backend.service.SalonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SalonPersistenceService implements SalonService {
    SalonRepository repository;
    SalonDtoMapper mapper;

    @Override
    public SalonDetailedDto getByIdDetailed(Long id){
        Salon salon = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found"));
        return mapper.mapToDto(salon);
    }

    @Override
    public Page<SalonPreviewDto> getAllPreview(String district, String search, Double minRating, Pageable pageable){
        return repository.findAll(pageable)
                .map(mapper::mapToPreviewDto);
    }

    @Override
    public List<DistrictDto> findDistrictStats(){
        return repository.findDistrictStats();
    }

    public void saveAll(List<Salon> salons){
        salons.forEach(this::save);
    }
    private void save(Salon salon){
        System.err.println(salon.toString());
        repository.save(salon);
    }
}
