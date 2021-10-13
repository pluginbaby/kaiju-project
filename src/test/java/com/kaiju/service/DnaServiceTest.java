package com.kaiju.service;

import com.kaiju.dto.DnaDTO;
import com.kaiju.dto.DnaTypeDTO;
import com.kaiju.dto.StatResponseDto;
import com.kaiju.model.Dna;
import com.kaiju.model.DnaType;
import com.kaiju.repository.DnaRepository;
import com.kaiju.repository.DnaTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class DnaServiceTest {

    @Spy
    @InjectMocks
    private DnaService dnaService;

    @Mock
    private DnaRepository dnaRepository;

    @Mock
    private DnaTypeService dnaTypeService;

    @Test
    public void getOrSave_GetSuccess() {
        DnaType dnaType = new DnaType();
        dnaType.setType("TYPE I");
        Dna dna = new Dna();
        dna.setDnaType(dnaType);
        dna.setSample("abc1234abc");
        DnaDTO dto = new DnaDTO();
        dto.setSample("abc1234abc");
        Mockito.when(dnaRepository.findBySample("abc1234abc")).thenReturn(Optional.of(dna));
        dnaService.getOrSave(dto);
        Mockito.verify(dnaRepository, times(0)).save(any());
        Mockito.verify(dnaTypeService, times(0)).getDnaTypeByTotal(any());
        Mockito.verify(dnaTypeService, times(0)).increaseTotal(dnaType);

    }

    @Test
    public void getOrSave_SaveSuccess() {
        DnaType dnaType = new DnaType();
        dnaType.setType("TYPE I");
        Dna dna = new Dna();
        dna.setDnaType(dnaType);
        dna.setSample("abc1234abc");
        DnaDTO dto = new DnaDTO();
        dto.setSample("abc1234abc");
        Mockito.when(dnaRepository.findBySample("abc1234abc")).thenReturn(Optional.empty());
        Mockito.when(dnaTypeService.getDnaTypeByTotal(any())).thenReturn(dnaType);
        dnaService.getOrSave(dto);
        ArgumentCaptor<Dna> captor = ArgumentCaptor.forClass(Dna.class);
        Mockito.verify(dnaRepository, times(1)).save(captor.capture());
        Dna captured = captor.getValue();
        Assertions.assertEquals(dnaType.getType(),captured.getDnaType().getType());
        Assertions.assertEquals(dto.getSample(),captured.getSample());
        Mockito.verify(dnaTypeService, times(1)).getDnaTypeByTotal(any());
        ArgumentCaptor<DnaType> captorDnaType = ArgumentCaptor.forClass(DnaType.class);
        Mockito.verify(dnaTypeService, times(1)).increaseTotal(captorDnaType.capture());
        DnaType capturedDnaType = captorDnaType.getValue();
        Assertions.assertEquals(dnaType.getType(),capturedDnaType.getType());

    }

    @Test
    public void getStatsOneType() {
        DnaType dnaType = new DnaType();
        dnaType.setTotal(1);
        dnaType.setType("Type I");
        DnaType dnaType2 = new DnaType();
        dnaType2.setTotal(1);
        dnaType2.setType("Type II");
        List<DnaType> dnaTypes = new ArrayList<>();
        dnaTypes.add(dnaType);
        dnaTypes.add(dnaType2);
        Mockito.when(dnaTypeService.findAll()).thenReturn(dnaTypes);
        Map<String, StatResponseDto> result = dnaService.getStats("Type I");
        Assertions.assertEquals(1, result.size());
        Assertions.assertNotNull(result.get("Type I"));
        Assertions.assertEquals(Double.valueOf("50.0"), result.get("Type I").getPercentage());
    }

    @Test
    public void getStatsAll() {
        DnaType dnaType = new DnaType();
        dnaType.setTotal(1);
        dnaType.setType("Type I");
        DnaType dnaType2 = new DnaType();
        dnaType2.setTotal(1);
        dnaType2.setType("Type II");
        List<DnaType> dnaTypes = new ArrayList<>();
        dnaTypes.add(dnaType);
        dnaTypes.add(dnaType2);
        Mockito.when(dnaTypeService.findAll()).thenReturn(dnaTypes);
        Map<String, StatResponseDto> result = dnaService.getStats("ALL");
        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get("Type I"));
        Assertions.assertNotNull(result.get("Type II"));
        Assertions.assertEquals(Double.valueOf("50.0"), result.get("Type I").getPercentage());
        Assertions.assertEquals(Double.valueOf("50.0"), result.get("Type II").getPercentage());

    }



}
