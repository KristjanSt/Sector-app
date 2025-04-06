package com.stuff.sector_app.domain;

import lombok.Data;

import java.util.List;

@Data
public class UserDataRequestDTO {

    private String name;

    private List<Long> sectors;

    private Boolean agreedToTerms;
}
