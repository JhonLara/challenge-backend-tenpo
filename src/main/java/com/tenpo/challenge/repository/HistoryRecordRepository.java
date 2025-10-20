package com.tenpo.challenge.repository;

import com.tenpo.challenge.entity.HistoryRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRecordRepository extends JpaRepository<HistoryRecord, Long> {
}
