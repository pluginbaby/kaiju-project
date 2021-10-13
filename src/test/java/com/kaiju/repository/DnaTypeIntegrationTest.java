package com.kaiju.repository;

import com.kaiju.model.DnaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DnaTypeIntegrationTest {

    @Autowired
    private DnaTypeRepository dnaTypeRepository;

    @Test
    public void findByTypeSuccess() {
        Assertions.assertTrue(dnaTypeRepository.findByType("TYPE I").isPresent());
    }
}
