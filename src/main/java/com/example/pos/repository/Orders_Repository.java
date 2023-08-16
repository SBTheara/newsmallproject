package com.example.pos.repository;

import com.example.pos.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Orders_Repository extends JpaRepository<Orders,Integer> {
}
