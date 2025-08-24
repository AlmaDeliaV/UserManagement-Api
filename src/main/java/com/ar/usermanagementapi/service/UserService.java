package com.ar.usermanagementapi.service;


import com.ar.usermanagementapi.api.models.UpdateRequestUEP;
import com.ar.usermanagementapi.api.models.User;

import java.util.List;

public interface UserService  {

    User getUser(long id);
    User getUserByEmail(String email);
    List<User> getUsers();
    void addUser(User user);
    void deleteUser(long id);
    String updatePassword(long id, String email, String password, String newPassword, String confirmPassword);
    String updateUser(long id, UpdateRequestUEP updateUser);
    long generateID();
    String setUsers(List<User> users);
}
