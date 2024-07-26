package com.pavvels.assessment.service;

import com.pavvels.assessment.model.SubjectScore;
import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.repository.TesteeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final TesteeRepository testeeRepository;
    private final MongoTemplate mongoTemplate;

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
        Query query = new Query();

        if (testeeIds != null && !testeeIds.isEmpty()) {
            List<String> ids = Arrays.asList(testeeIds.split(","));
            query.addCriteria(Criteria.where("testeeId").in(ids));
        }

        if (subjects != null && !subjects.isEmpty()) {
            List<String> subjectList = Arrays.asList(subjects.split(","));
            query.addCriteria(Criteria.where("subjects.subject").in(subjectList));
        }

        if (totalRange != null && !totalRange.isEmpty()) {
            String[] range = totalRange.split("-");
            double min = Double.parseDouble(range[0]);
            double max = Double.parseDouble(range[1]);
            query.addCriteria(Criteria.where("totalScore").gte(min).lte(max));
        }

        query.with(Sort.by(Sort.Direction.DESC, "totalScore")).with(Sort.by(Sort.Direction.ASC, "testeeId"));

        return mongoTemplate.find(query, Testee.class);
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
