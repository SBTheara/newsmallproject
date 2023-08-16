package com.example.pos.repository;

import com.example.pos.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users_Repository extends JpaRepository<Users,Integer> {
}
