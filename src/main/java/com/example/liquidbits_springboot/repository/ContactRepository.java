package com.example.liquidbits_springboot.repository;

import com.example.liquidbits_springboot.model.Contact;
import com.example.liquidbits_springboot.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
