package com.example.springbootpostgresqlrest.repository;

import com.example.springbootpostgresqlrest.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> { }
