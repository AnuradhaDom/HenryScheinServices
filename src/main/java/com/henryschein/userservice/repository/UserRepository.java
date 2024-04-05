package com.henryschein.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.henryschein.userservice.model.User;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer> {

    public User findFirstById(int id);

    public User findByEmail(String email);

    public List<User> findByName(String name);

    public int countByName(String name);

    public int countByEmail(String email);

}
