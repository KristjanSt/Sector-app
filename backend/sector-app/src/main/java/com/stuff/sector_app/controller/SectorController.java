package com.stuff.sector_app.controller;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping("all")
    public List<Sector> getAllSectors(){
        return sectorService.getAllSectors();
    }

    @GetMapping("/top-level")
    public List<Sector> getTopLevelSectors() {
        return sectorService.getAllTopLevelSectors();
    }

    @GetMapping("/by-ids")
    public List<Sector> getSectorsByIds(@RequestParam List<Long> requestedIds) {
        return sectorService.getAllSectorsByIds(requestedIds);
    }
}
