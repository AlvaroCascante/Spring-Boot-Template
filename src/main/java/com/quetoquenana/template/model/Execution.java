package com.quetoquenana.template.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "executions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Execution {
    @Id
    @JsonView({ExecutionResponseView.ExecutionList.class})
    private UUID id;

    @Column(name = "executed_at", nullable = false)
    @JsonView({ExecutionResponseView.ExecutionList.class})
    private LocalDateTime executedAt;

    @Column(name = "server_name")
    @JsonView({ExecutionResponseView.ExecutionDetail.class})
    private String serverName;

    @Column(name = "ip_address")
    @JsonView({ExecutionResponseView.ExecutionDetail.class})
    private String ipAddress;

    @Column(name = "app_version")
    @JsonView({ExecutionResponseView.ExecutionList.class})
    private String appVersion;

    @Column(name = "environment")
    @JsonView({ExecutionResponseView.ExecutionList.class})
    private String environment;
}
