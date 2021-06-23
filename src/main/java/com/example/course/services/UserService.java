package com.example.course.services;

import java.util.List;
import java.util.Optional;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;
import com.example.course.services.exceptions.DatabaseException;
import com.example.course.services.exceptions.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
       return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        try{
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){ // tentando deletar um obj com id que nao existe
            throw new ResourceNotFoundException(id);
        }
        catch(DataIntegrityViolationException e){ // tentando deletar um obj com objs associados a ele
            throw new DatabaseException(e.getMessage());
        }
        
    }

    public User update(Long id, User obj){
        User entity = userRepository.getById(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
