package com.kaiju.repository;

import com.kaiju.model.Dna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<Dna, Long> {

    Optional<Dna> findBySample(String sample);
}
