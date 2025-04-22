package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.domain.UserData;
import com.stuff.sector_app.domain.UserDataResponseDTO;
import com.stuff.sector_app.repository.UserDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDataServiceTests {

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private SectorService sectorService;

    @InjectMocks
    private UserDataService userDataService;

    @Test
    void saveUserData_newlyCreatedUser() {
        String name = "John Doe";
        List<Long> sectorIds = List.of(1L, 2L);
        boolean agreedToTerms = true;

        Set<Sector> selectedSectors = createSectorsFromIds(sectorIds);
        UserData savedUser = createUser(name, selectedSectors, agreedToTerms, 10L);

        when(userDataRepository.findByName(name)).thenReturn(Optional.empty());
        when(sectorService.getAllSectorsByIds(sectorIds)).thenReturn(List.copyOf(selectedSectors));
        when(userDataRepository.save(any(UserData.class))).thenReturn(savedUser);

        UserDataResponseDTO response = userDataService.saveUserData(name, sectorIds, agreedToTerms);

        assertUserResponse(response, name, sectorIds, agreedToTerms);
        assertSavedUser(name, selectedSectors, agreedToTerms);

        verify(sectorService).getAllSectorsByIds(sectorIds);
        verify(userDataRepository).findByName(name);
    }

    @Test
    void saveUserData_existingUserUpdated() {
        String name = "Jane Doe";
        List<Long> sectorIds = List.of(3L);
        boolean agreedToTerms = false;

        Set<Sector> selectedSectors = createSectorsFromIds(sectorIds);
        UserData existingUser = createUser(name, new HashSet<>(), true, 20L);
        UserData updatedUser = createUser(name, selectedSectors, agreedToTerms, 20L);

        when(userDataRepository.findByName(name)).thenReturn(Optional.of(existingUser));
        when(sectorService.getAllSectorsByIds(sectorIds)).thenReturn(List.copyOf(selectedSectors));
        when(userDataRepository.save(any(UserData.class))).thenReturn(updatedUser);

        UserDataResponseDTO response = userDataService.saveUserData(name, sectorIds, agreedToTerms);

        assertUserResponse(response, name, sectorIds, agreedToTerms);
        assertSavedUser(name, selectedSectors, agreedToTerms);

        verify(sectorService).getAllSectorsByIds(sectorIds);
        verify(userDataRepository).findByName(name);
    }

    @Test
    void saveUserData_emptySectorIds() {
        String name = "Peter Pan";
        List<Long> sectorIds = List.of();
        boolean agreedToTerms = true;

        UserData savedUser = createUser(name, Set.of(), agreedToTerms, 30L);

        when(userDataRepository.findByName(name)).thenReturn(Optional.empty());
        when(sectorService.getAllSectorsByIds(sectorIds)).thenReturn(List.of());
        when(userDataRepository.save(any(UserData.class))).thenReturn(savedUser);

        UserDataResponseDTO response = userDataService.saveUserData(name, sectorIds, agreedToTerms);

        assertUserResponse(response, name, sectorIds, agreedToTerms);
        assertSavedUser(name, Set.of(), agreedToTerms);

        verify(sectorService).getAllSectorsByIds(sectorIds);
        verify(userDataRepository).findByName(name);
    }

    private Set<Sector> createSectorsFromIds(List<Long> ids) {
        return ids.stream().map(id -> {
            Sector sector = new Sector();
            sector.setId(id);
            return sector;
        }).collect(Collectors.toSet());
    }

    private UserData createUser(String name, Set<Sector> sectors, boolean agreed, Long id) {
        UserData user = new UserData();
        user.setName(name);
        user.setSelectedSectors(sectors);
        user.setAgreedToTerms(agreed);
        user.setId(id);
        return user;
    }

    private void assertUserResponse(UserDataResponseDTO response, String name, List<Long> sectorIds, boolean agreed) {
        assertNotNull(response);
        assertEquals(name, response.name());
        assertEquals(sectorIds, response.sectorIds());
        assertEquals(agreed, response.agreedToTerms());
    }

    private void assertSavedUser(String name, Set<Sector> expectedSectors, boolean agreed) {
        ArgumentCaptor<UserData> captor = ArgumentCaptor.forClass(UserData.class);
        verify(userDataRepository).save(captor.capture());

        UserData saved = captor.getValue();
        assertEquals(name, saved.getName());
        assertEquals(expectedSectors, saved.getSelectedSectors());
        assertEquals(agreed, saved.getAgreedToTerms());
    }
}