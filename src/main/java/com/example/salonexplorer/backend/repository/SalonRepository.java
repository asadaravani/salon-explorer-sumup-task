package com.example.salonexplorer.backend.repository;

import com.example.salonexplorer.backend.api.dto.DistrictDto;
import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

    @Query("""
SELECT new com.example.salonexplorer.backend.api.dto.DistrictDto(
    s.address.district,
    COUNT(s)
)
FROM Salon s
WHERE s.address.district IS NOT NULL
GROUP BY s.address.district
""")
    List<DistrictDto> findDistrictStats();
}
