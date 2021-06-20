package com.example.course.services;

import java.util.List;
import java.util.Optional;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service // registrando classe como component(Service) do Spring pra ser auto-injetado pelo Spring
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
       Optional<User> obj = userRepository.findById(id);
       return obj.get();
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }
}
