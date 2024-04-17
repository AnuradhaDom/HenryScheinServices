package com.henryschein.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.henryschein.userservice.model.User;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    public User findFirstById(int id);

    public User findByEmail(String email);

    //public User findByuserName(String userName);

    public Optional<User> findByUserName(String userName);

   // public int countByName(String name);

   // public int countByEmail(String email);

//   public User getUser(String userName);


}
