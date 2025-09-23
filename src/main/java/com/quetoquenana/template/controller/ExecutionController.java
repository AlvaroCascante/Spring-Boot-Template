package com.quetoquenana.template.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.quetoquenana.template.model.Execution;
import com.quetoquenana.template.model.ExecutionResponseView;
import com.quetoquenana.template.util.JsonViewPageUtil;
import com.quetoquenana.template.service.ExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @PreAuthorize("hasRole('SYSTEM')")
    public ResponseEntity<Execution> getExecutionById(@PathVariable UUID id) {
        log.info("GET /api/executions/{} called", id);
        Execution execution = executionService.getExecutionById(id);
        if (execution == null) {
            log.error("Execution with id {} not found", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(execution);
    }

    /**
     * Get executions with pagination
     * @param page page number (default 0)
     * @param size page size (default 10)
     * @return ResponseEntity with paginated executions
     */
    @GetMapping("/page")
    @JsonView(ExecutionResponseView.ExecutionList.class)
    @PreAuthorize("hasRole('SYSTEM')")
    public ResponseEntity<JsonViewPageUtil<Execution>> getExecutionsPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        log.info("GET /api/executions/page?page={}&size={} called", page, size);
        Page<Execution> executionsPage = executionService.getExecutionsPage(page, size);
        return ResponseEntity.ok(new JsonViewPageUtil<>(executionsPage, executionsPage.getPageable()));
    }
}