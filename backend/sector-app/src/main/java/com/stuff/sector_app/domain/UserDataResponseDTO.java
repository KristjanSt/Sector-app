package com.stuff.sector_app.domain;

import java.util.List;

public record UserDataResponseDTO(String name, List<Long> sectorIds, Boolean agreedToTerms) {}
