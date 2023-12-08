package com.tags.cs451r_groupproject_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tags.cs451r_groupproject_backend.application.dto.ApplicationDTO;
import com.tags.cs451r_groupproject_backend.application.model.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        "x",
                        "x",
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))));
    }
    @Test
    void callingGetApplicationsShouldReturnSuccessfulResponse() throws Exception {
        mockMvc.perform(get("/applications"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void callingGetApplicationByIdShouldReturnSuccessfulResponse() throws Exception {
        mockMvc.perform(get("/applications/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")))
                .andExpect(content().string(containsString("Smith")))
                .andExpect(content().string(containsString("\"studentId\":1")));
    }

    @Test
    void callingPostApplicationShouldReturnSuccessfulResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Application application = new Application();
        application.setDesiredClasses(new ArrayList<>());
        application.setDesiredTypes(new ArrayList<>());
        application.setId(999L);

        String json = mapper.writeValueAsString(application);


        ApplicationDTO applicationDTO = mapper.readValue(mockMvc.perform(post("/applications").param("student_id", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString(), ApplicationDTO.class);

        mockMvc.perform(delete("/applications/" + applicationDTO.getId()));
    }

    @Test
    void callingDeleteApplicationShouldReturnSuccessfulResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Application application = new Application();
        application.setDesiredClasses(new ArrayList<>());
        application.setDesiredTypes(new ArrayList<>());
        application.setId(999L);

        String json = mapper.writeValueAsString(application);

        ApplicationDTO applicationDTO = mapper.readValue(
                mockMvc.perform(post("/applications").param("student_id", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andReturn().getResponse().getContentAsString(), ApplicationDTO.class);

        mockMvc.perform(delete("/applications/" + applicationDTO.getId()))
                .andExpect(status().is2xxSuccessful());
    }

}
