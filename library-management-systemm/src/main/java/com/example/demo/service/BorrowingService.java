package com.example.demo.service;
import com.example.demo.model.Book;
import com.example.demo.model.Patron;
import com.example.demo.model.BorrowingRecord;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronRepository patronRepository;

    @Cacheable("borrowingRecords")
    public List<BorrowingRecord> getAllBorrowingRecords() {
        return borrowingRecordRepository.findAll();
    }

    @Cacheable(value = "borrowingRecords", key = "#id")
    public Optional<BorrowingRecord> getBorrowingRecordById(Long id) {
        return borrowingRecordRepository.findById(id);
    }

    @CacheEvict(value = "borrowingRecords", allEntries = true)
    public BorrowingRecord borrowBook(Long bookId, Long patronId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new RuntimeException("Patron not found"));

        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }

    @CacheEvict(value = "borrowingRecords", key = "#record.id")
    public BorrowingRecord returnBook(Long bookId, Long patronId) {
        BorrowingRecord record = borrowingRecordRepository
                .findByBookIdAndPatronId(bookId, patronId)
                .orElseThrow(() -> new RuntimeException("Borrowing record not found"));
        record.setReturnDate(LocalDate.now());
        return borrowingRecordRepository.save(record);
    }
}
