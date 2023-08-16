package com.example.pos;

import com.example.pos.entity.Products;
import com.example.pos.entity.Users;
import com.example.pos.repository.Products_Repository;
import com.example.pos.repository.Users_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class PosApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(PosApplication.class, args);
    }
    @Autowired
    private Products_Repository productsRepository;
    @Autowired
    private Users_Repository usersRepository;
    @Override
    public void run(String... args) throws Exception {
    }
}
