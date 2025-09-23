package com.quetoquenana.template.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.quetoquenana.template.model.Execution;
import com.quetoquenana.template.model.ExecutionResponseView;
import com.quetoquenana.template.service.ExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/executions")
public class ExecutionController {
    private final ExecutionService executionService;

    public ExecutionController(ExecutionService executionService) {
        this.executionService = executionService;
    }

    /**
     * Get all executions
     * @return ResponseEntity with list of executions
     */
    @GetMapping
    @JsonView(ExecutionResponseView.ExecutionList.class)
    public ResponseEntity<List<Execution>> getAllExecutions() {
        log.info("GET /api/executions called");
        List<Execution> executions = executionService.getAllExecutions();
        return ResponseEntity.ok(executions);
    }

    /**
     * Get execution by id
     * @param id UUID of execution
     * @return ResponseEntity with execution or 404 if not found
     */
    @GetMapping("/{id}")
    @JsonView(ExecutionResponseView.ExecutionDetail.class)
    public ResponseEntity<Execution> getExecutionById(@PathVariable UUID id) {
        log.info("GET /api/executions/{} called", id);
        Execution execution = executionService.getExecutionById(id);
        if (execution == null) {
            log.error("Execution with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(execution);
    }
}