package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Patron;
import com.example.demo.repository.PatronRepository;
import com.example.demo.service.PatronService;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PatronServiceTest {

    @InjectMocks
    private PatronService patronService;

    @Mock
    private PatronRepository patronRepository;

    private Patron patron;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        patron = new Patron();
        patron.setId(1L);
        patron.setName("Test Patron");
        patron.setContactInfo("test@domain.com");
    }

    @Test
    void testGetPatronById() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        Optional<Patron> foundPatron = patronService.getPatronById(1L);
        assertEquals("Test Patron", foundPatron.get().getName());
    }

    @Test
    void testSavePatron() {
        when(patronRepository.save(patron)).thenReturn(patron);
        Patron savedPatron = patronService.savePatron(patron);
        assertEquals("Test Patron", savedPatron.getName());
    }

    @Test
    void testUpdatePatron() {
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(patronRepository.save(patron)).thenReturn(patron);
        patron.setName("Updated Name");
        Patron updatedPatron = patronService.updatePatron(1L, patron);
        assertEquals("Updated Name", updatedPatron.getName());
    }

    @Test
    void testDeletePatron() {
        patronService.deletePatron(1L);
        verify(patronRepository, times(1)).deleteById(1L);
    }
}