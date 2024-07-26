package com.pavvels.assessment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document
@NoArgsConstructor
@Getter
@Setter
public class Testee {

    @Id
    private String id;
    private String testeeId;
    private List<SubjectScore> subjects;
    private double totalScore;
    private double averageScore;

    public Testee(String testeeId, List<SubjectScore> subjects) {
        this.testeeId = testeeId;
        this.subjects = subjects;
    }
}
