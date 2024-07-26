package com.pavvels.assessment.service;

import com.pavvels.assessment.model.SubjectScore;
import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.repository.TesteeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class EvaluationServiceTests {

    @Mock
    private TesteeRepository testeeRepository;

    @InjectMocks
    private EvaluationService evaluationService;

    @Test
    public void testSaveTestScores() {
        Testee testee = new Testee("1", List.of(new SubjectScore("maths", 100, 80, 20, .0)));
        evaluationService.saveTestScores(testee);
        verify(testeeRepository).save(testee);
    }

    @Test
    public void testCalculateScores() {
        SubjectScore subjectScore = new SubjectScore("maths", 100, 80, 20, .0);
        Testee testee = new Testee("1", List.of(subjectScore));
        evaluationService.saveTestScores(testee);

        assertEquals(75.0, subjectScore.getScore(), 0.01);
        assertEquals(75.0, testee.getTotalScore(), 0.01);
        assertEquals(75.0, testee.getAverageScore(), 0.01);
    }
}
