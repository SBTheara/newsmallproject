package com.example.pos.repository;

import com.example.pos.entity.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sales_Repository extends JpaRepository<Sales,Integer> {
}
