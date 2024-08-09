package com.example.demo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.demo.model.Book;
import com.example.demo.model.BorrowingRecord;
import com.example.demo.model.Patron;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BorrowingRecordRepository;
import com.example.demo.repository.PatronRepository;
import com.example.demo.service.BorrowingService;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class BorrowingServiceTest {

    @InjectMocks
    private BorrowingService borrowingService;

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private PatronRepository patronRepository;

    private BorrowingRecord borrowingRecord;
    private Book book;
    private Patron patron;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        book = new Book();
        book.setId(1L);
        book.setTitle("Test Book");

        patron = new Patron();
        patron.setId(1L);
        patron.setName("Test Patron");

        borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(1L);
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setBorrowDate(LocalDate.now());
    }

    @Test
    void testBorrowBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.save(borrowingRecord)).thenReturn(borrowingRecord);

        BorrowingRecord savedRecord = borrowingService.borrowBook(1L, 1L);
        assertEquals(book.getTitle(), savedRecord.getBook().getTitle());
        assertEquals(patron.getName(), savedRecord.getPatron().getName());
    }

    @Test
    void testReturnBook() {
        when(borrowingRecordRepository.findByBookIdAndPatronId(1L, 1L))
                .thenReturn(Optional.of(borrowingRecord));

        BorrowingRecord returnedRecord = borrowingService.returnBook(1L, 1L);
        assertEquals(LocalDate.now(), returnedRecord.getReturnDate());
        verify(borrowingRecordRepository, times(1)).save(returnedRecord);
    }
}