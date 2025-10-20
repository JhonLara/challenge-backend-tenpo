package com.company.project.repository;

import com.company.project.entity.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {
}
