package com.pavvels.assessment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SubjectScore {
    private String subject;
    private int totalQuestions;
    private int correct;
    private int incorrect;

    public SubjectScore(String subject, int totalQuestions, int correct, int incorrect) {
        this.subject = subject;
        this.totalQuestions = totalQuestions;
        this.correct = correct;
        this.incorrect = incorrect;
    }
}
