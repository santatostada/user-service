package com.santatostada.database.service;

import com.santatostada.database.entity.User;
import com.santatostada.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(User user){
        userRepository.save(user);
    }


    public User findById(int id){
        return userRepository.findById(id);
    }

    public String updateUser(int id, String status){
        if (status.equals("away") && userRepository.findById(id).getStatus().equals("online")){
            Thread thread = Thread.getAllStackTraces().keySet().stream().filter(x -> x.getName()
                    .equals(String.valueOf(id))).findFirst().orElse(null);
            if(thread != null){
                thread.interrupt();
            }
            ThreadService threadService = new ThreadService(userRepository, id, status);
            threadService.start();
        }
        else {
            userRepository.updateUser(id, status);
        }
        return userRepository.findById(id).getStatus();
    }
}


