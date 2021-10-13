package com.kaiju.service;

import com.kaiju.dto.DnaDTO;
import com.kaiju.dto.DnaTypeDTO;
import com.kaiju.dto.StatResponseDto;
import com.kaiju.model.Dna;
import com.kaiju.model.DnaType;
import com.kaiju.repository.DnaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kaiju.utils.DnaUtils.calculateSum;

@Service
public class DnaService {

    private static final String MESSAGE_ABOVE_80 = "UNKNOWN SAMPLES REACHED THRESHOLD";
    private static final String MESSAGE_UNDER_80 = "UNKNOWN SAMPLES UNDER ACCEPTED THRESHOLD";


    private DnaRepository dnaRepository;
    private DnaTypeService dnaTypeService;

    @Autowired
    public DnaService(final DnaRepository dnaRepository, final DnaTypeService dnaTypeService) {
        this.dnaRepository = dnaRepository;
        this.dnaTypeService = dnaTypeService;
    }

    public DnaTypeDTO getOrSave(DnaDTO dto) {
        return dnaRepository.
                findBySample(dto.getSample()).
                map(this::toDto).
                orElseGet(()-> save(dto));
    }

    public Map<String,StatResponseDto> getStats(String type) {
        List<DnaType> dnaTypes = dnaTypeService.findAll();
        int dnaTypesTotal = dnaTypes.stream().mapToInt(DnaType::getTotal).sum();
        Map<String, StatResponseDto> result = new HashMap<>();
        if (type.equals("ALL")) {
            dnaTypes.stream().forEach(dnaType -> result.put(dnaType.getType(),buildStat(dnaType, dnaTypesTotal)));
        }
        else {
            DnaType dnaType = dnaTypes.stream().filter(dna -> type.equals(dna.getType())).findFirst().get();
            result.put(dnaType.getType(),buildStat(dnaType, dnaTypesTotal));
        }
        return result;

    }

    private StatResponseDto buildStat(DnaType dnaType, Integer dnaTypesTotal) {
        Double percentage = (dnaType.getTotal() * 100) / Double.valueOf(dnaTypesTotal);
        StatResponseDto response = new StatResponseDto();
        response.setPercentage(percentage);
        if (dnaType.getType().equals("UNKNOWN")) {
            response.setMessage(MESSAGE_UNDER_80);
            if (response.getPercentage() > 80 ) {
                response.setMessage(MESSAGE_ABOVE_80);
            }
        }
        return response;
    }

    private DnaTypeDTO save (DnaDTO dto) {
        int sum = calculateSum(dto.getSample());
        DnaType dnaType = dnaTypeService.getDnaTypeByTotal(sum);
        Dna dna = new Dna();
        dna.setSample(dto.getSample());
        dna.setDnaType(dnaType);
        dnaRepository.save(dna);
        dnaTypeService.increaseTotal(dnaType);
        return toDto(dna);
    }

    private DnaTypeDTO toDto(Dna dna) {
        DnaTypeDTO dnaTypeDTO = new DnaTypeDTO();
        dnaTypeDTO.setType(dna.getDnaType().getType());
        return dnaTypeDTO;
    }

}
