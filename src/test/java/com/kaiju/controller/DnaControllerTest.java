package com.kaiju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kaiju.dto.DnaDTO;
import com.kaiju.service.DnaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DnaController.class)
public class DnaControllerTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DnaService dnaService;

    @Test
    public void createDnaSuccess() throws Exception {
        DnaDTO dnaDTO = new DnaDTO();
        dnaDTO.setSample("1235abc123");
        this.mockMvc.perform(post("/dna")
                .content(OBJECT_MAPPER.writeValueAsString(dnaDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dnaService).getOrSave(any());
    }

    @Test
    public void createDnaBadRequest() throws Exception {
        DnaDTO dnaDTO = new DnaDTO();
        //only numbers, letters are not present
        dnaDTO.setSample("1235");
        this.mockMvc.perform(post("/dna")
                .content(OBJECT_MAPPER.writeValueAsString(dnaDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(dnaService, times(0)).getOrSave(any());
    }

    @Test
    public void getStatsTypeISuccess() throws Exception {

        this.mockMvc.perform(get("/dna/stats").param("type", "Type I")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dnaService).getStats("Type I");
    }

    @Test
    public void getStatsAllSuccess() throws Exception {

        this.mockMvc.perform(get("/dna/stats")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(dnaService).getStats("ALL");
    }
}
