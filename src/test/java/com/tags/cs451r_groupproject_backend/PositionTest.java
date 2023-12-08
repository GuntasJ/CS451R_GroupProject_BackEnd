package com.tags.cs451r_groupproject_backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tags.cs451r_groupproject_backend.application.dto.ApplicationDTO;
import com.tags.cs451r_groupproject_backend.application.model.Application;
import com.tags.cs451r_groupproject_backend.position.dto.PositionDTO;
import com.tags.cs451r_groupproject_backend.position.model.Position;
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
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PositionTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(
                                "x",
                                "x",
                                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")))
        );
    }

    @Test
    void callingGetPositionsShouldReturnSuccessfulResponse() throws Exception {
        mockMvc.perform(get("/admin/positions"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void callingGetPositionByIdShouldReturnSuccessfulResponse() throws Exception {
        mockMvc.perform(get("/admin/positions/CS 101/Grader"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void callingPostApplicationShouldReturnSuccessfulResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Position position = new Position();
        position.setSemesters(List.of("Summer"));
        position.setRequiredStanding("BS");
        position.setPositionClass("CS 999");
        position.setPositionType("Grader");

        String json = mapper.writeValueAsString(position);


        PositionDTO positionDTO = mapper.readValue(mockMvc.perform(post("/admin/positions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().is2xxSuccessful())
                .andReturn()
                .getResponse()
                .getContentAsString(), PositionDTO.class);

        mockMvc.perform(delete("/admin/positions/" + positionDTO.getPositionClass() + "/" + positionDTO.getPositionType()));
    }

    @Test
    void callingDeletePositionShouldReturnSuccessfulResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Position position = new Position();
        position.setSemesters(List.of("Summer"));
        position.setRequiredStanding("BS");
        position.setPositionClass("CS 999");
        position.setPositionType("Grader");

        String json = mapper.writeValueAsString(position);

        PositionDTO positionDTO = mapper.readValue(
                mockMvc.perform(post("/admin/positions")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                                .characterEncoding("utf-8"))
                        .andReturn().getResponse().getContentAsString(), PositionDTO.class);

        mockMvc.perform(delete("/admin/positions/" + positionDTO.getPositionClass() + "/" + positionDTO.getPositionType()))
                .andExpect(status().is2xxSuccessful());
    }


}
