package com.pavvels.assessment.service;

import com.pavvels.assessment.model.SubjectScore;
import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.repository.TesteeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationService {

    private final TesteeRepository testeeRepository;

    public EvaluationService(TesteeRepository testeeRepository) {
        this.testeeRepository = testeeRepository;
    }

    public void saveTestScores(Testee testee) {
        for (SubjectScore subject : testee.getSubjects()) {
            double score = subject.getCorrect() * 1.0 - subject.getIncorrect() * 0.25;
            subject.setScore(score);
        }
        calculateTotalAndAverage(testee);
        testeeRepository.save(testee);
    }

    @SuppressWarnings("unused")
    public List<Testee> getEvaluatedScores(String testeeIds, String subjects, String totalRange, String averageRange, String scoreRange) {
        return testeeRepository.findAll();
    }

    private void calculateTotalAndAverage(Testee testee) {
        double total = 0;
        for (SubjectScore subject : testee.getSubjects()) {
            total += subject.getScore();
        }
        double average = total / testee.getSubjects().size();
        testee.setTotalScore(total);
        testee.setAverageScore(average);
    }
}
