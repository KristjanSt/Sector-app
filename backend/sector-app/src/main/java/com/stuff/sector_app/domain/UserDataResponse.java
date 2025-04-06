package com.stuff.sector_app.domain;

import java.util.Set;

public record UserDataResponse(String name, Set<Sector> selectedSectors, Boolean agreedToTerms) {
}
