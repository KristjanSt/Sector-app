package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SectorService {


    private final SectorRepository sectorRepository;

    public List<Sector> getAllSectorsByIds(List<Long> ids) {
        return sectorRepository.findAllById(ids);
    }

    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    public List<Sector> buildSectorTree() {
        List<Sector> rootSectors = sectorRepository.findByParentIdIsNull();
        Map<Long, List<Sector>> parentToChildMap = sectorRepository.findAll().stream()
                .filter(sector -> sector.getParentId() != null)
                .collect(Collectors.groupingBy(Sector::getParentId));

        for (Sector sector : sectorRepository.findAll())
            sector.setChildren(new ArrayList<>());

        for (Sector root : rootSectors)
            populateChildren(root, parentToChildMap);

        return rootSectors;
    }

    private void populateChildren(Sector parent, Map<Long, List<Sector>> parentToChildrenMap) {
        List<Sector> children = parentToChildrenMap.get(parent.getId());

        if (children != null) {
            parent.getChildren().addAll(children);
            for (Sector child : children)
                populateChildren(child, parentToChildrenMap);
        }
    }
}
