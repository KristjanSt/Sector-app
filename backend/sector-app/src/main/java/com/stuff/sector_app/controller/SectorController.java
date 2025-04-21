package com.stuff.sector_app.controller;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sectors")
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    @GetMapping
    public List<Sector> getAllSectors(){
        return sectorService.getAllSectors();
    }

    @GetMapping("/tree")
    public List<Sector> getSectorsAsTree(){
        return sectorService.buildSectorTree();
    }
}
