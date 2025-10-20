package com.tenpo.challenge.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "history_records")
@Getter
@Setter
public class HistoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime createdAt;

    @Column(nullable = false, length = 255)
    private String endpoint;

    @Column(columnDefinition = "text")
    private String parameters;

    @Column(columnDefinition = "text")
    private String response;

    @Column(columnDefinition = "text")
    private String error;
}
