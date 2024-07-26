package com.pavvels.assessment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectScore {
    private String subject;
    private int totalQuestions;
    private int correct;
    private int incorrect;
    private double score;

}
