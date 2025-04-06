package com.stuff.sector_app.controller;

import com.stuff.sector_app.domain.UserData;
import com.stuff.sector_app.domain.UserDataRequestDTO;
import com.stuff.sector_app.domain.UserDataResponse;
import com.stuff.sector_app.service.UserDataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-data")
public class UserDataController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/save")
    public ResponseEntity<String> saveFormData(@RequestBody UserDataRequestDTO userDataRequestDTO, HttpSession session) {
        String name = userDataRequestDTO.getName();
        List<Long> sectors = userDataRequestDTO.getSectors();
        Boolean agreedToTerms = userDataRequestDTO.getAgreedToTerms();

        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId == null) {
            sessionId = userDataService.generateSessionId();
            session.setAttribute("sessionId", sessionId);
        }

        userDataService.saveUserData(name, sectors, agreedToTerms, sessionId);

        return new ResponseEntity<>("Data saved successfully", HttpStatus.OK);
    }

    @GetMapping("/session")
    public ResponseEntity<?> getUserDataBySessionId(HttpSession session) {
        String sessionId = (String) session.getAttribute("sessionId");
        if (sessionId == null) return new ResponseEntity<>("No active session found.", HttpStatus.NOT_FOUND);

        Optional<UserData> userDataOptional = userDataService.getUserDataBySessionId(sessionId);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            UserDataResponse response = new UserDataResponse(userData.getName(), userData.getSelectedSectors(), userData.getAgreedToTerms());
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>("User data not found for this session", HttpStatus.NOT_FOUND);
        }
    }
}
