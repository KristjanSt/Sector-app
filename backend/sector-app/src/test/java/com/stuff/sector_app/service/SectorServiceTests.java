package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.repository.SectorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SectorServiceTests {

    @Mock
    private SectorRepository sectorRepository;

    @InjectMocks
    private SectorService sectorService;

    @Test
    void getAllSectorsByIds_returnsMatchingSectors() {
        List<Long> ids = List.of(1L, 2L);
        List<Sector> expectedSectors =
                List.of(createSector(1L, null), createSector(2L, null));

        when(sectorRepository.findAllById(ids)).thenReturn(expectedSectors);

        List<Sector> actual = sectorService.getAllSectorsByIds(ids);

        assertEquals(expectedSectors, actual);
        verify(sectorRepository).findAllById(ids);
    }

    @Test
    void getAllSectors_returnsAllSectors() {
        List<Sector> sectors =
                List.of(createSector(1L, null), createSector(2L, 1L));

        when(sectorRepository.findAll()).thenReturn(sectors);

        List<Sector> actual = sectorService.getAllSectors();

        assertEquals(sectors, actual);
        verify(sectorRepository).findAll();
    }

    @Test
    void buildSectorTree_buildsCorrectHierarchy() {
        Sector root = createSector(1L, null);
        Sector child1 = createSector(2L, 1L);
        Sector child2 = createSector(3L, 1L);
        Sector grandchild = createSector(4L, 2L);

        List<Sector> allSectors = List.of(root, child1, child2, grandchild);
        List<Sector> rootSectors = List.of(root);

        when(sectorRepository.findByParentIdIsNull()).thenReturn(rootSectors);
        when(sectorRepository.findAll()).thenReturn(allSectors);

        List<Sector> result = sectorService.buildSectorTree();

        assertEquals(1, result.size());
        assertEquals(2, root.getChildren().size());
        assertEquals(1, child1.getChildren().size());
        assertEquals(0, child2.getChildren().size());
        assertEquals(grandchild, child1.getChildren().getFirst());

        verify(sectorRepository).findAll();
        verify(sectorRepository).findByParentIdIsNull();
    }

    @Test
    void buildSectorTree_withNoRootSectors_returnsEmptyList() {
        when(sectorRepository.findByParentIdIsNull()).thenReturn(List.of());
        when(sectorRepository.findAll()).thenReturn(List.of());

        List<Sector> result = sectorService.buildSectorTree();

        assertTrue(result.isEmpty());
        verify(sectorRepository).findAll();
        verify(sectorRepository).findByParentIdIsNull();
    }

    @Test
    void buildSectorTree_handlesCircularReference() {
        Sector a = createSector(1L, 2L);
        Sector b = createSector(2L, 1L);

        List<Sector> allSectors = List.of(a, b);
        List<Sector> rootSectors = List.of();

        when(sectorRepository.findByParentIdIsNull()).thenReturn(rootSectors);
        when(sectorRepository.findAll()).thenReturn(allSectors);

        List<Sector> result = sectorService.buildSectorTree();

        assertTrue(result.isEmpty());
        verify(sectorRepository).findAll();
        verify(sectorRepository).findByParentIdIsNull();
    }

    private Sector createSector(Long id, Long parentId) {
        Sector sector = new Sector();
        sector.setId(id);
        sector.setParentId(parentId);
        sector.setChildren(new ArrayList<>());
        return sector;
    }
}
