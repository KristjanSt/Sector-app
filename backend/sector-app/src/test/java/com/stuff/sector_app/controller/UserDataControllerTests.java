package com.stuff.sector_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stuff.sector_app.domain.UserDataRequestDTO;
import com.stuff.sector_app.service.UserDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserDataController.class)
class UserDataControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDataService userDataService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveFormData_returnsOkWhenDataValid() throws Exception {
        UserDataRequestDTO request = new UserDataRequestDTO();
        request.setName("Alice");
        request.setSectors(List.of(1L, 2L));
        request.setAgreedToTerms(true);

        mockMvc.perform(post("/api/user-data/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isOk());

        verify(userDataService).saveUserData("Alice", List.of(1L, 2L), true);
    }
}
