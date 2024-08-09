package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.BorrowingRecord;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    Optional<BorrowingRecord> findByBookIdAndPatronId(Long bookId, Long patronId);
}
