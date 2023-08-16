package com.example.pos.controller;

import com.example.pos.entity.Users;
import com.example.pos.repository.Users_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/users")
public class UsersController {
    @Autowired
    private Users_Repository usersRepository;
    @GetMapping(value = "/getalldata")
    public ResponseEntity<List<Users>> getallCustomer(){
        return new ResponseEntity<List<Users>>(usersRepository.findAll(),HttpStatus.OK);
    }
    @GetMapping(value = "/getbyid/{id}")
    public ResponseEntity<Users> getByUserID(@PathVariable(value = "id") Integer id){
        return new ResponseEntity<Users>(usersRepository.findById(id).get(),HttpStatus.OK);
    }
    @PostMapping(value = "/adduser")
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        return new ResponseEntity<Users>(usersRepository.save(user),HttpStatus.CREATED);
    }
    @PutMapping(value = "/updateuser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(value = "id") Integer id, @RequestBody Users users ){
        Users user = new Users();
        user = usersRepository.findById(id).get();
        user.setUser_ID(id);
        user.setFirstname(users.getFirstname());
        user.setLastname(users.getLastname());
        user.setEmail(users.getEmail());
        user.setPassword(users.getPassword());
        user.setAddress(users.getAddress());
        user.setPhone(users.getPhone());
        user.setType(users.getType());
        user.setCreate_at(users.getCreate_at());
        usersRepository.save(user);
        return new ResponseEntity<String>("This user was updated",HttpStatus.OK);
    }
    @DeleteMapping(value = "/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(value = "id") Integer id){
        usersRepository.deleteById(id);
        return new ResponseEntity<String>("This user was deleted",HttpStatus.OK);
    }
}
