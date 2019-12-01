package com.santatostada.database.service;

import com.santatostada.database.repository.UserRepository;
import lombok.Setter;

@Setter
public class ThreadService extends Thread {
    private UserRepository userRepository;
    private int id;
    private String status;

    public ThreadService (UserRepository userRepository, int id, String status){
        this.userRepository = userRepository;
        this.id = id;
        this.status = status;
        this.setName(String.valueOf(id));
    }

    @Override
    public void run(){
        if (!Thread.interrupted()){
            try{
                sleep(300000);
                userRepository.updateUser(id, status);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else return;
    }
}
