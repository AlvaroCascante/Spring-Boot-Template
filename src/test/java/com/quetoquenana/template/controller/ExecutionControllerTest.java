package com.quetoquenana.template.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.quetoquenana.template.model.Execution;
import com.quetoquenana.template.model.ExecutionResponseView;
import com.quetoquenana.template.model.ResponseView;
import com.quetoquenana.template.service.ExecutionService;
import com.quetoquenana.template.util.JsonViewPageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExecutionControllerTest {
    @Mock
    private ExecutionService executionService;

    @InjectMocks
    private ExecutionController executionController;

    private Execution execution;
    private UUID executionId;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        executionId = UUID.randomUUID();
        execution = new Execution(
            executionId,
            LocalDateTime.now(),
            "server1",
            "127.0.0.1",
            "1.0.0",
            "dev"
        );
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Test
    void testGetAllExecutions_ReturnsList() throws Exception {
        // Given: a list with one execution returned by the service
        List<Execution> executions = Collections.singletonList(execution);
        when(executionService.getAllExecutions()).thenReturn(executions);

        // When: the controller's getAllExecutions is called
        ResponseEntity<List<Execution>> response = executionController.getAllExecutions();

        // Then: the response should be 200 OK and contain the executions list
        assertEquals(ResponseEntity.ok().build().getStatusCode(), response.getStatusCode());
        assertEquals(executions, response.getBody());
        verify(executionService, times(1)).getAllExecutions();

        // And: the JSON payload should include only the fields for ExecutionList view
        String json = objectMapper.writerWithView(ExecutionResponseView.ExecutionList.class).writeValueAsString(response.getBody());
        assertTrue(json.contains("id"));
        assertTrue(json.contains("executedAt"));
        assertTrue(json.contains("appVersion"));
        assertTrue(json.contains("environment"));
        assertFalse(json.contains("serverName"));
        assertFalse(json.contains("ipAddress"));
    }

    @Test
    void testGetExecutionById_Found() throws Exception {
        // Given: the service returns a valid execution for the given id
        when(executionService.getExecutionById(executionId)).thenReturn(execution);

        // When: the controller's getExecutionById is called
        ResponseEntity<Execution> response = executionController.getExecutionById(executionId);

        // Then: the response should be 200 OK and contain the execution
        assertEquals(ResponseEntity.ok().build().getStatusCode(), response.getStatusCode());
        assertEquals(execution, response.getBody());
        verify(executionService, times(1)).getExecutionById(executionId);

        // And: the JSON payload should include all fields for ExecutionDetail view
        String json = objectMapper.writerWithView(ExecutionResponseView.ExecutionDetail.class).writeValueAsString(response.getBody());
        assertTrue(json.contains("id"));
        assertTrue(json.contains("executedAt"));
        assertTrue(json.contains("appVersion"));
        assertTrue(json.contains("environment"));
        assertTrue(json.contains("serverName"));
        assertTrue(json.contains("ipAddress"));
    }

    @Test
    void testGetExecutionById_NotFound() {
        // Given: the service returns null for the given id
        when(executionService.getExecutionById(executionId)).thenReturn(null);

        // When: the controller's getExecutionById is called
        ResponseEntity<Execution> response = executionController.getExecutionById(executionId);

        // Then: the response should be 404 Not Found and body should be null
        assertEquals(ResponseEntity.notFound().build().getStatusCode(), response.getStatusCode());
        assertNull(response.getBody());
        verify(executionService, times(1)).getExecutionById(executionId);
    }

    @Test
    void testGetExecutionsPage_ReturnsJsonViewPageUtil() throws Exception {
        // Given: a page with one execution returned by the service
        Page<Execution> page = new PageImpl<>(Collections.singletonList(execution), PageRequest.of(0, 10), 1);
        when(executionService.getExecutionsPage(0, 10)).thenReturn(page);

        // When: the controller's getExecutionsPage is called
        ResponseEntity<JsonViewPageUtil<Execution>> response = executionController.getExecutionsPage(0, 10);

        // Then: the response should be 200 OK and contain a JsonViewPageUtil
        assertEquals(ResponseEntity.ok().build().getStatusCode(), response.getStatusCode());
        assertNotNull(response.getBody());
        // Serialize only the list of Execution objects with the view
        String json = objectMapper.writerWithView(ExecutionResponseView.ExecutionList.class)
                .writeValueAsString(response.getBody().getContent());
        assertTrue(json.contains("id"));
        assertTrue(json.contains("executedAt"));
        assertTrue(json.contains("appVersion"));
        assertTrue(json.contains("environment"));
        assertFalse(json.contains("serverName"));
        assertFalse(json.contains("ipAddress"));
        // Also check pagination metadata
        String metaJson = objectMapper.writerWithView(ResponseView.Always.class)
                .writeValueAsString(response.getBody());
        assertTrue(metaJson.contains("totalPages"));
        assertTrue(metaJson.contains("totalElements"));
    }
}
