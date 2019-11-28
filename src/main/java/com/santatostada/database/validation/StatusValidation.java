package com.santatostada.database.validation;

public class StatusValidation {


    public boolean validate(String status){
        return (status.equals("online") || status.equals("offline") || status.equals("away"));
    }
}
