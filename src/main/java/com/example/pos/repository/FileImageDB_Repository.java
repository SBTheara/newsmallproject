package com.example.pos.repository;

import com.example.pos.entity.FileImageDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FileImageDB_Repository extends JpaRepository<FileImageDB,String> {
}
