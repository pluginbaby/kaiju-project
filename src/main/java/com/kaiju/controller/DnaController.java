package com.kaiju.controller;

import com.kaiju.dto.DnaDTO;
import com.kaiju.dto.DnaTypeDTO;
import com.kaiju.dto.StatResponseDto;
import com.kaiju.service.DnaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/dna")
public class DnaController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DnaController.class);


    @Autowired
    private DnaService dnaService;

    @PostMapping()
    public DnaTypeDTO create(@RequestBody @Valid DnaDTO request) {
        LOGGER.info("Received request for create Dna {}", request);
        return dnaService.getOrSave(request);
    }

    @GetMapping(value = "/stats")
    public Map<String,StatResponseDto> getStats(@RequestParam (defaultValue = "ALL") String type) {
        LOGGER.info("Receive request for retrieve Dna with type: {}", type);
        return dnaService.getStats(type);
    }


}
