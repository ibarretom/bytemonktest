package com.bytemonk.securityincidents.reports.adapters.presentation;

import com.bytemonk.securityincidents.abstractions.domain.services.DateFactory;
import com.bytemonk.securityincidents.reports.application.domain.valueobjects.CreateIncidentReportRequest;
import com.bytemonk.securityincidents.reports.domain.valueobjects.ESecurityLevel;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.text.MessageFormat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class ReportControllerIT {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Autowired
    public ReportControllerIT(MockMvc aMockMvc, ObjectMapper aObjectMapper) {
        this.mockMvc = aMockMvc;
        this.objectMapper = aObjectMapper;
    }
    @BeforeEach
    @Sql(scripts = "/import.sql")
    void init() {}

    @Test
    public void should_be_able_to_create_a_report() throws Exception {
        var aBody = new CreateIncidentReportRequest("Microsoft crash", "Seams that they got a bug",
                                                    ESecurityLevel.HIGH.toString(), DateFactory.now());
        var aJSONBody = objectMapper.writeValueAsString(aBody);

        var authorizationHeader = new HttpHeaders();
        authorizationHeader.add("X-Authorization-Username", "gandalfthegrey");
        authorizationHeader.add("X-Authorization-Password", "thewhite");

        mockMvc
                .perform(
                    post("/report")
                            .contentType("Application/json")
                            .content(aJSONBody)
                            .headers(authorizationHeader)
                )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber());
    }

    @Test
    public void should_not_be_able_to_access_without_credentials() throws Exception {
        var aBody = new CreateIncidentReportRequest("Microsoft crash", "Seams that they got a bug",
                ESecurityLevel.HIGH.toString(), DateFactory.now());
        var aJSONBody = objectMapper.writeValueAsString(aBody);

        mockMvc
                .perform(
                        post("/report")
                                .contentType("Application/json")
                                .content(aJSONBody)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_not_be_able_to_access_with_wrong_credentials() throws Exception {
        var aBody = new CreateIncidentReportRequest("Microsoft crash", "Seams that they got a bug",
                ESecurityLevel.HIGH.toString(), DateFactory.now());

        var aJSONBody = objectMapper.writeValueAsString(aBody);

        var authorizationHeader = new HttpHeaders();
        authorizationHeader.add("X-Authorization-Username", "gandalfthegrey");
        authorizationHeader.add("X-Authorization-Password", "thewhites");

        mockMvc
                .perform(
                        post("/report")
                                .contentType("Application/json")
                                .content(aJSONBody)
                                .headers(authorizationHeader)
                )
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void should_warn_the_correct_error_for_invalid_body() throws Exception {
        var aJSONBody =
                "{\"title\": \"Microsoft crash\", \"description\": \"\", \"severityLevel\": \"HIGH\", \"incidentDate\": \"" + DateFactory.now().toString() + "\"}";

        var authorizationHeader = new HttpHeaders();
        authorizationHeader.add("X-Authorization-Username", "gandalfthegrey");
        authorizationHeader.add("X-Authorization-Password", "thewhite");

        mockMvc
                .perform(
                        post("/report")
                                .contentType("Application/json")
                                .content(aJSONBody)
                                .headers(authorizationHeader)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}
