package com.kaiju.repository;

import com.kaiju.model.Dna;
import com.kaiju.model.DnaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DnaRepositoryIntegrationTest {

    @Autowired
    private DnaRepository dnaRepository;
    @Autowired
    private DnaTypeRepository dnaTypeRepository;

    @Test
    public void findBySampleSuccess() {
        DnaType dnaType = dnaTypeRepository.findByType("TYPE I").get();
        Dna dna = new Dna();
        dna.setDnaType(dnaType);
        dna.setSample("abc1234");
        dnaRepository.save(dna);
        Assertions.assertTrue(dnaRepository.findBySample("abc1234").isPresent());
        dnaRepository.delete(dna);
    }

    @Test
    public void findBySampleError() {
        DnaType dnaType = dnaTypeRepository.findByType("TYPE I").get();
        Dna dna = new Dna();
        dna.setDnaType(dnaType);
        dna.setSample("abc1234");
        dnaRepository.save(dna);
        Assertions.assertFalse(dnaRepository.findBySample("abc123").isPresent());
        dnaRepository.delete(dna);

    }
}
