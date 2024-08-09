package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Patron;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
