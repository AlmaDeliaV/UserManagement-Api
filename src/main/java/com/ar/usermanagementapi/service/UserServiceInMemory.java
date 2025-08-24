package com.ar.usermanagementapi.service;

import com.ar.usermanagementapi.api.models.UpdateRequestUEP;
import com.ar.usermanagementapi.api.models.User;
import com.ar.usermanagementapi.exception.userException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Profile("In-Memory")
public class UserServiceInMemory implements UserService {
    private List<User> users;

    public UserServiceInMemory() {
        users = new ArrayList<>();

        User user1 = new User(1,"user1","user1@gmail.com","user1-password");
        User user2 = new User(2,"user2","user2@gmail.com","user2-password");
        User user3 = new User(3,"user3","user3@gmail.com","user3-password");
        User user4 = new User(4,"user4","user4@gmail.com","user4-password");
        User user5 = new User(5,"user5","user5@gmail.com","user5-password");
        users.addAll(Arrays.asList(user1,user2,user3,user4,user5));


    }
    @Override
    public User getUser(long id){
        if(users==null)
        {
            return null;
        }
        return users.stream().filter(user -> user.getId()==id).findFirst().orElse(null);

    }

    @Override
    public User getUserByEmail(String email){
        if(users==null)
        {
            return null;
        }
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().
                orElse(null);
    }

    @Override
    public List<User> getUsers()
    {
        if(users.isEmpty()) return  null;
        return users;
    }

    @Override
    public String setUsers(List<User> u){
        /*
        users.addAll(users);

        List<User> users2= new ArrayList<>();
        users2.addAll(users);
        users2.sort(Comparator.comparing(User::getId));
        u.sort(Comparator.comparing(User::getId));
        if(u.getFirst().getId()!=users2.getLast().getId()){
            users.addAll(u);
            users2.removeAll(users2);
        }*/
        String ms="the ids can´t add to users: ";
        boolean b=false;
        for(User user:u){
            if(users.stream().anyMatch(user1 -> user.getId()==user1.getId())){
                b=true;
                ms=ms+user.getId()+", ";
            }else{
                users.add(user);
            }
        }
        if(b){
            return ms;
        }else {
            return "users add susefully";
        }



    }

    @Override
    public void addUser(User user)
    {
        if(users.stream().anyMatch(user1 -> user1.getId()==user.getId())){
            throw   new userException("User with "+ user.getId() + " already exists");
        }
        if(user.getPassword()==null){
            throw   new userException("Password empty");
        }
        if(users.stream().anyMatch(u->u.getEmail().equals(user.getEmail()))){
            throw   new userException("the email .....@gmail.com already exists");
        }
        users.add(user);
    }

    //delete
    @Override
    public void deleteUser(long id)
    {
        if(users==null)
        {
            return;
        }
        if(users.stream().anyMatch(u -> u.getId()==id)){
            User user=getUser(id);
            users.remove(user);
        }else {
            throw   new userException("User with "+ id + " don't exists");
        }

    }

    @Override
    public String updatePassword(long id,String email,String password,String newPassword,String confirmPassword)
    {
        if(users==null)
        {
            return "Error user no found";
        }
        for(User user: users){
            if(user.getId()==id){
                if(!user.getEmail().equals(email)){
                    return "Error user email don't match";
                }
                if(!user.getPassword().equals(password)){
                    return "Error user password Don't Match";
                }
                if(!newPassword.equals(confirmPassword)){
                    return "Error confirmation password don't Match";
                }
                user.setPassword(newPassword);
                return "Success, password updated";
            }
        }
        return "Error, something was wrong, can't change password";
    }

    @Override
    public String updateUser(long id, UpdateRequestUEP updateUser) {
        if(users.stream().anyMatch(u -> u.getId()==id)) {
            User user = getUser(id);
            // return user.getPassword();

            if (!user.getPassword().equals(updateUser.getPassword())) {
                throw new userException("User with " + id + " password don't match, please use the correct password, to update your name o email");
            }

            if (updateUser.getUsername() != null) {
                user.setName(updateUser.getUsername());
            }
            if (updateUser.getEmail() != null) {
                user.setEmail(updateUser.getEmail());
            }
            return "Success, user updated";


        }
        throw   new userException("User with "+ id + " don't exists");
    }

    public long generateID() {
        /*
        Optional<User> id=users.stream().max(Comparator.comparing(User:: getId));
        Long idUser = id.map(User::getId).orElse(0L);
        return idUser+1;*/
        return  users.stream()
                .mapToLong(User :: getId)
                .max()
                .orElse(0L)+1;

    }
}
