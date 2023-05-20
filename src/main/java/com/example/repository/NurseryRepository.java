package com.example.repository;

import com.example.domain.nursery.Nursery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseryRepository extends JpaRepository<Nursery, Long> {
}
