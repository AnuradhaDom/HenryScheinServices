package com.henryshain.userservice.repository;

import org.springframework.data.repository.CrudRepository;
import com.henryshain.userservice.models.User;


public interface UserHSRepository extends CrudRepository<User, Integer> {

    public User findFirstById(int id);

    public User findByEmail(String email);

    public User findByName(String name);

    public int countByName(String name);

    public int countByEmail(String email);

}
