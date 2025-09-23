package com.quetoquenana.template.service;

import com.quetoquenana.template.model.Execution;
import java.util.List;
import java.util.UUID;

public interface ExecutionService {
    List<Execution> getAllExecutions();
    Execution getExecutionById(UUID id);
    void saveExecutionOnStartup();
}
