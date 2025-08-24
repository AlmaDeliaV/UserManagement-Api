package com.ar.usermanagementapi.api.controller;

import com.ar.usermanagementapi.api.models.PasswordUpdateR;
import com.ar.usermanagementapi.api.models.UpdateRequestUEP;
import com.ar.usermanagementapi.api.models.User;
import com.ar.usermanagementapi.exception.userException;

import com.ar.usermanagementapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


    private final UserService userService;

    @Autowired
    public  UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/get-user/{id}")
    public User getUserById(@PathVariable long id){
        User user=userService.getUser(id);
        if(user != null){
            return user;
        }
        return null;
    }

    @GetMapping("users/get-users")
    public List<User> getAllUsers(){
        List<User> users =userService.getUsers();
        return users;
    }

    @PostMapping("/users/set-users")
    public String setUser(@RequestBody List<User> user){

        String res=userService.setUsers(user);
        return res;
    }

    @PostMapping("users/add-user")
    public String addUser(@RequestBody UpdateRequestUEP user){
        User user1= new User (userService.generateID(),
                user.getUsername(),user.getEmail(),user.getPassword());
        userService.addUser(user1);
        return "User added Successfully";
    }
    @PostMapping("users/add-user2")
    public String addUser2(@RequestBody User user){
        userService.addUser(user);
        return "User added Successfully";
    }

    @ExceptionHandler(userException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserException(userException e){
        return e.getMessage();
    }

    @PutMapping("/users/update-pass/{id}")
    public String updatePass(@PathVariable long id, @RequestBody PasswordUpdateR  passwordUpdateR){
        String answ=userService.updatePassword(
                id,
                passwordUpdateR.getEmail(),
                passwordUpdateR.getPassword(),
                passwordUpdateR.getNewPassword(),
                passwordUpdateR.getConfirmPassword());
        return answ;
    }

    @PutMapping("/users/update-user/{id}")
    public String updateUser(@PathVariable long id, @RequestBody UpdateRequestUEP user){
        String answ=userService.updateUser(id,user);
        return answ;
    }

    @DeleteMapping("/users/delete-user/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "User Deleted Successfully";
    }


}
