package com.henryschein.userservice.service;

import com.henryschein.userservice.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public User createUser(User user);
    public List<User> createUsers(List<User> users);
    public List<User> getUsers();
    public Optional<User> getUserById(int id);
    public List<User> getUserByName(String name);
    public User getUserByEmailId(String emailId);
    public String deleteUser(int id);
    public String deleteUsers(List<Integer> ids);
    public User updateUser(User user);
    public List<User> updateUsers(List<User> users);


}
