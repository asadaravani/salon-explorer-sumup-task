package com.example.salonexplorer.backend.repository;

import com.example.salonexplorer.backend.entity.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalonRepository extends JpaRepository<Salon, Long> {

}
