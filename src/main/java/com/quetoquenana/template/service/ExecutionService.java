package com.quetoquenana.template.service;

import com.quetoquenana.template.model.Execution;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExecutionService {
    List<Execution> findAll();
    Optional<Execution> findById(UUID id);
    void saveExecutionOnStartup();
    Page<Execution> findAll(Pageable pageable);
}
