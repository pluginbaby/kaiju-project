package com.kaiju.service;

import com.kaiju.model.DnaType;
import com.kaiju.repository.DnaTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DnaTypeService {

    private DnaTypeRepository dnaTypeRepository;

    @Autowired
    public DnaTypeService(final DnaTypeRepository dnaTypeRepository) {
        this.dnaTypeRepository = dnaTypeRepository;
    }

    public DnaType getDnaTypeByTotal(Integer sum) {
        return dnaTypeRepository.findAll().stream().filter(dnaType -> dnaType.isBetweenRange(sum)).findFirst().get();
    }

    public DnaType increaseTotal(DnaType dnaType) {
        dnaType.setTotal(dnaType.getTotal() + 1);
        return dnaTypeRepository.save(dnaType);
    }

    public List<DnaType> findAll() {
        return dnaTypeRepository.findAll();
    }
}
