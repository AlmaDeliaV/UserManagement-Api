package com.ar.usermanagementapi.service;

import com.ar.usermanagementapi.api.models.UpdateRequestUEP;
import com.ar.usermanagementapi.api.models.User;
import com.ar.usermanagementapi.exception.userException;
import com.ar.usermanagementapi.server.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("db")
public class UserServiceBD implements UserService {

    UserRepository userRepository;


    public UserServiceBD(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public User getUser(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new userException("User with " + id + " not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new userException("User with email " + email + " not found"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        if (userRepository.existsById(user.getId())) {
            throw new userException("User with " + user.getId() + " already exists");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new userException("User with " + user.getEmail() + " already exists");
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        if (!userRepository.existsById(id)) {
            throw new userException("User with " + id + " not found");
        }
        userRepository.deleteById(id);
    }

    @Override
    public String updatePassword(long id, String email, String password, String newPassword, String confirmPassword) {
        User user = getUser(id);
        if (!user.getEmail().equals(email)) {
            return "Error: user email doesn't match";
        }
        if (!user.getPassword().equals(password)) {
            return "Error: user password doesn't match";
        }
        if (!newPassword.equals(confirmPassword)) {
            return "Error: confirmation password doesn't match";
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return "Success: password updated";
    }

    @Override
    public String updateUser(long id, UpdateRequestUEP updateUser) {
        User user = getUser(id);
        if (!user.getPassword().equals(updateUser.getPassword())) {
            throw new userException("Incorrect password, cannot update");
        }
        if (updateUser.getUsername() != null) {
            user.setName(updateUser.getUsername());
        }
        if (updateUser.getEmail() != null) {
            user.setEmail(updateUser.getEmail());
        }
        userRepository.save(user);
        return "Success: user updated";
    }

    @Override
    public long generateID() {
        return userRepository.findAll()
                .stream()
                .mapToLong(User::getId)
                .max()
                .orElse(0L) + 1;
    }

    @Override
    public String setUsers(List<User> users) {
        userRepository.saveAll(users);
        return "Users added successfully";
    }


}
