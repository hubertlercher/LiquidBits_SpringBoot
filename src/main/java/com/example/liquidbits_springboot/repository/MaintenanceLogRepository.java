package com.example.liquidbits_springboot.repository;

import com.example.liquidbits_springboot.model.Container;
import com.example.liquidbits_springboot.model.MaintenanceLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface MaintenanceLogRepository extends JpaRepository<MaintenanceLog, Integer> {


}
