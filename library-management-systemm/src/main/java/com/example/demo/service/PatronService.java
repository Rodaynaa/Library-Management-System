package com.example.demo.service;
import com.example.demo.model.Patron;
import com.example.demo.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    @Cacheable("patrons")
    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    @Cacheable(value = "patrons", key = "#id")
    public Optional<Patron> getPatronById(Long id) {
        return patronRepository.findById(id);
    }

    @CacheEvict(value = "patrons", allEntries = true)
    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @CacheEvict(value = "patrons", key = "#id")
    public Patron updatePatron(Long id, Patron patronDetails) {
        Patron patron = patronRepository.findById(id).orElseThrow();
        patron.setName(patronDetails.getName());
        patron.setContactInfo(patronDetails.getContactInfo());
        return patronRepository.save(patron);
    }

    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        patronRepository.deleteById(id);
    }
}