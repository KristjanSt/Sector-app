package com.stuff.sector_app.controller;

import com.stuff.sector_app.domain.UserDataRequestDTO;
import com.stuff.sector_app.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-data")
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormData(@RequestBody UserDataRequestDTO userData) {
        String name = userData.getName();
        List<Long> sectors = userData.getSectors();
        Boolean agreedToTerms = userData.getAgreedToTerms();

        userDataService.saveUserData(name, sectors, agreedToTerms);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
