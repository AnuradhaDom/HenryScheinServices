package com.henryschein.userservice.controller;

import com.henryschein.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.henryschein.userservice.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService userService;



    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }



    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }

    @PostMapping("/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> createUsers(@RequestBody List<User> users){
        return userService.createUsers(users);
    }
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/byId/{id}")
    public User getUserById(@PathVariable int id){
       return userService.getUserById(id).get();
        //orElseThrow(() -> new RuntimeException("User not found with id: " +id));
    }

    @GetMapping("/byName/{name}")
    public List<User> getUserByName(@PathVariable String name){
       return userService.getUserByName(name);
    }
    @GetMapping("/byEmailId/{emailId}")
    public User getUserByEmailId(@PathVariable String emailId){
        return userService.getUserByEmailId(emailId);
    }
   @DeleteMapping("/deleteUser/{id}")
   public String deleteUser(@PathVariable int id){
       return userService.deleteUser(id);
   }
   @DeleteMapping("/deleteUsers/{ids}")
   public String deleteUsers(@PathVariable List<Integer> ids){
        return userService.deleteUsers(ids);
   }
  @PutMapping("/updateUser")
   public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
   }
   @PutMapping("/updateUsers")
   public List<User> updateUsers(@RequestBody List<User> users){
       return userService.updateUsers(users);
   }

}
