package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAllTopLevelSectors() {
        return sectorRepository.findByParentIdIsNull();
    }

    public List<Sector> getAllSectorsByIds(List<Long> ids) {
        return sectorRepository.findAllById(ids);
    }

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }
}
