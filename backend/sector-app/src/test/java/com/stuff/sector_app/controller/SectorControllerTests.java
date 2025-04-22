package com.stuff.sector_app.controller;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.service.SectorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SectorController.class)
class SectorControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SectorService sectorService;

    @Test
    void getAllSectors_returnsListOfSectors() throws Exception {
        Sector sector = new Sector();
        sector.setId(1L);
        sector.setName("IT");

        when(sectorService.getAllSectors()).thenReturn(List.of(sector));

        mockMvc.perform(get("/api/sectors"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("IT"));
    }

    @Test
    void getSectorsAsTree_returnsSectorHierarchy() throws Exception {
        Sector root = new Sector();
        root.setId(1L);
        root.setName("Root");

        when(sectorService.buildSectorTree()).thenReturn(List.of(root));

        mockMvc.perform(get("/api/sectors/tree"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("Root"));
    }
}
