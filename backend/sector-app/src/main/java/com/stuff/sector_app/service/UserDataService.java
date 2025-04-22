package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.domain.UserData;
import com.stuff.sector_app.domain.UserDataResponseDTO;
import com.stuff.sector_app.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final SectorService sectorService;

    @Transactional
    public UserDataResponseDTO saveUserData(String name, List<Long> sectorIds, Boolean agreedToTerms) {
        UserData userData = userDataRepository.findByName(name)
                .orElseGet(() -> {
                    UserData newUser = new UserData();
                    newUser.setName(name);
                    return newUser;
                });

        Set<Sector> selectedSectors = new HashSet<>(sectorService.getAllSectorsByIds(sectorIds));
        userData.setSelectedSectors(selectedSectors);
        userData.setAgreedToTerms(agreedToTerms);

        UserData saved = userDataRepository.save(userData);
        return new UserDataResponseDTO(
                saved.getName(),
                saved.getSelectedSectors().stream()
                        .map(Sector::getId)
                        .collect(Collectors.toList()),
                saved.getAgreedToTerms()
        );
    }
}
