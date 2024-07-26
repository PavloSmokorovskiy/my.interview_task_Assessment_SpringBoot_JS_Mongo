package com.pavvels.assessment.controller;

import com.pavvels.assessment.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(EvaluationController.class)
public class EvaluationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluationService evaluationService;

    @Test
    public void testSubmitTestScores() throws Exception {
        mockMvc.perform(post("/evaluation/sheets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testeeId\": \"342\", \"subjects\": [{\"subject\": \"maths\", \"totalQuestions\": 100, \"correct\": 72, \"incorrect\": 15}]}"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetEvaluatedScores() throws Exception {
        mockMvc.perform(get("/evaluation/scores"))
                .andExpect(status().isOk());
    }

}
