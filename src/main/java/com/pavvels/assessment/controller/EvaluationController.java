package com.pavvels.assessment.controller;

import com.pavvels.assessment.model.Testee;
import com.pavvels.assessment.service.EvaluationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/evaluation")
public class EvaluationController {

    private final EvaluationService evaluationService;

    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @PostMapping("/sheets")
    public ResponseEntity<?> submitTestScores(@RequestBody Testee testee) {
        try {
            evaluationService.saveTestScores(testee);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error processing request: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/scores")
    public ResponseEntity<?> getEvaluatedScores(@RequestParam(required = false) String testeeIds,
                                                @RequestParam(required = false) String subjects,
                                                @RequestParam(required = false) String totalRange,
                                                @RequestParam(required = false) String averageRange,
                                                @RequestParam(required = false) String scoreRange) {
        try {
            return new ResponseEntity<>(evaluationService.getEvaluatedScores(testeeIds, subjects, totalRange, averageRange, scoreRange), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error retrieving scores: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
