package com.stuff.sector_app.service;

import com.stuff.sector_app.domain.Sector;
import com.stuff.sector_app.domain.UserData;
import com.stuff.sector_app.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserDataService {
    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private SectorService sectorService;

    public Optional<UserData> getUserDataBySessionId(String sessionId) {
        return userDataRepository.findBySessionId(sessionId);
    }

    @Transactional
    public UserData saveUserData(String name, List<Long> sectorIds, Boolean agreedToTerms, String sessionId) {
        UserData userData = userDataRepository.findBySessionId(sessionId).orElse(new UserData());
        userData.setName(name);

        Set<Sector> selectedSectors = null;
        if (sectorIds != null && !sectorIds.isEmpty()) {
            selectedSectors = new HashSet<>(sectorService.getAllSectorsByIds(sectorIds));
        }
        userData.setSelectedSectors(selectedSectors);

        userData.setAgreedToTerms(agreedToTerms);
        userData.setSessionId(sessionId);
        return userDataRepository.save(userData);
    }

    public String generateSessionId() {
        return UUID.randomUUID().toString();
    }
}
