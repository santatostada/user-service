package com.santatostada.database.controller;

import com.santatostada.database.entity.User;
import com.santatostada.database.validation.EmailValidation;
import com.santatostada.database.validation.PhoneValidation;
import com.santatostada.database.exceptions.UserException;
import com.santatostada.database.service.UserService;
import com.santatostada.database.validation.StatusValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping("/addUser")
    public Map<String, Integer> addUser(@Valid @NotBlank @RequestParam(name="user_name") String userName,
                                    @Valid @NotBlank @RequestParam(name="phone_number") String phoneNumber,
                                    @Valid @NotBlank @RequestParam(name="user_status") String userStatus,
                                    @Valid @NotBlank @RequestParam(name="user_email") String userEmail) {
        EmailValidation emailValidation = new EmailValidation();
        PhoneValidation phoneValidation = new PhoneValidation();
        StatusValidation statusValidation = new StatusValidation();
        if (!emailValidation.validate(userEmail))
            throw new IllegalArgumentException("incorrect email");
        if (!phoneValidation.validate(phoneNumber))
            throw new IllegalArgumentException("incorrect phone number");
        if (!statusValidation.validate(userStatus))
            throw new IllegalArgumentException("incorrect status");
        User user = new User(userName, phoneNumber, userStatus, userEmail);
        userService.addUser(user);
        Map<String, Integer> result = new HashMap<String, Integer>();
        result.put("id", user.getId());
        return result;
    }



    @RequestMapping("/updateStatus")
    public Map<String, String> updateStatus(@Valid @NotNull @RequestParam(name="id") int id,
                               @Valid @NotBlank @RequestParam(name="user_status") String userStatus) {
        StatusValidation statusValidation = new StatusValidation();
        if (!statusValidation.validate(userStatus))
            throw new IllegalArgumentException("incorrect status");
        String oldStatus = userService.updateUser(id, userStatus);
        Map<String, String> result = new HashMap<>();
        result.put("id", String.valueOf(userService.findById(id).getId()));
        result.put("old_status", oldStatus);
        result.put("new_status", userStatus);
        return result;
    }

    @RequestMapping("/getUser")
    public User getUser(@Valid @NotNull @RequestParam(name="id") int id) {
        if (userService.findById(id) == null)
            throw new NullPointerException("user doesn't exist");
        return userService.findById(id);
    }

    @ExceptionHandler(Exception.class)
    public UserException userException(Exception ex) {
        UserException userException = new UserException();
        userException.setCause(ex.getMessage());
        return userException;
    }


}
