package com.pavvels.assessment.controller;

import com.pavvels.assessment.model.SubjectScore;
import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.service.EvaluationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    public void testGetEvaluatedScoresSuccess() throws Exception {
        Testee testee = new Testee("342", List.of(new SubjectScore("maths", 100, 72, 15, 68.25)));
        when(evaluationService.getEvaluatedScores(any(), any(), any(), any(), any())).thenReturn(List.of(testee));

        mockMvc.perform(get("/evaluation/scores")
                        .param("testeeIds", "342")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].testeeId").value("342"))
                .andExpect(jsonPath("$[0].subjects[0].subject").value("maths"))
                .andExpect(jsonPath("$[0].subjects[0].score").value(68.25));
    }

    @Test
    public void testSubmitTestScoresErrorHandling() throws Exception {
        doThrow(new RuntimeException("Failed to save scores")).when(evaluationService).saveTestScores(any());

        mockMvc.perform(post("/evaluation/sheets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testeeId\": \"342\", \"subjects\": [{\"subject\": \"maths\", \"totalQuestions\": 100, \"correct\": 72, \"incorrect\": 15}]}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Error processing request")));
    }

}
