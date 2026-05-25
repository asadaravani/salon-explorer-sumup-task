package com.example.salonexplorer.backend.service.impl;

import com.example.salonexplorer.backend.api.dto.DistrictDto;
import com.example.salonexplorer.backend.api.dto.SalonDetailedDto;
import com.example.salonexplorer.backend.api.dto.SalonPreviewDto;
import com.example.salonexplorer.backend.api.dto.SalonUpdateDto;
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
import org.springframework.data.jpa.domain.Specification;
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
        Salon salon = findById(id);
        return mapper.mapToDto(salon);
    }

    @Override
    public Page<SalonPreviewDto> getAllPreview(List<String> districts, String search, Double minRating, Pageable pageable){
        List<String> safeDistricts = (districts == null || districts.isEmpty()) ? null : districts;
        Specification<Salon> spec = Specification
                .where(SalonSpecification.districtIn(safeDistricts))
                .and(SalonSpecification.nameOrTypeLike(search))
                .and(SalonSpecification.ratingGreaterThan(minRating));

        return repository.findAll(spec,pageable)
                .map(mapper::mapToPreviewDto);
    }

    @Override
    public List<DistrictDto> findDistrictStats(){
        return repository.findDistrictStats();
    }

    @Override
    public void updateSalon(Long id, SalonUpdateDto updateDto){
        Salon salon = findById(id);
        Salon updated = mapper.updateSalon(updateDto, salon);
        repository.save(updated);
    }


    public void saveAll(List<Salon> salons){
        salons.forEach(this::save);
    }

    public boolean hasData(){
        return repository.count() > 0;
    }

    private Salon findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Salon not found for id: " + id));
    }

    private void save(Salon salon){
        System.out.println(salon.toString());
        repository.save(salon);
    }
}
