package com.pavvels.assessment.service;

import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.repository.TesteeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    private final TesteeRepository testeeRepository;

    public EvaluationService(TesteeRepository testeeRepository) {
        this.testeeRepository = testeeRepository;
    }

    public void saveTestScores(Testee testee) {
        testeeRepository.save(testee);
    }

    public List<Testee> getEvaluatedScores(String testeeIds, String subjects, String totalRange, String averageRange, String scoreRange) {
        return testeeRepository.findAll();
    }
}
