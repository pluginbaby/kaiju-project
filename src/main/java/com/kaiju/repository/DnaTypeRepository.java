package com.kaiju.repository;

import com.kaiju.model.DnaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaTypeRepository extends JpaRepository<DnaType, Long> {

    Optional<DnaType> findByType(String type);
}
