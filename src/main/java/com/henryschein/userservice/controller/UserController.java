package com.henryschein.userservice.controller;

import com.henryschein.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.henryschein.userservice.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/user")
    public String user() {
        return ("<h1>Welcome User</h1>");
    }

    @GetMapping("/admin")
    //@PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return ("<h1>Welcome Admin</h1>");
    }


    @PostMapping("/adduser")
    //@PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    
    
    
    

    @PostMapping("/addusers")
    @ResponseStatus(HttpStatus.CREATED)
    public List<User> createUsers(@RequestBody List<User> users){
        return userService.createUsers(users);
    }
    
    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/byid/{id}")
    //@PreAuthorize("hasRole('USER')")
    public User getUserById(@PathVariable int id){
       return userService.getUserById(id).get();
        //orElseThrow(() -> new RuntimeException("User not found with id: " +id));
    }

    @GetMapping("/byname/{name}")
    public Optional<User> getUserByName(@PathVariable String name){
    	
    
       return userService.getUser(name);
    }
    
    
    @GetMapping("/byemail/{emailId}")
    //@PreAuthorize("hasRole('ADMIN')")
    public User getUserByEmailId(@PathVariable String emailId){
        return userService.getUserByEmailId(emailId);
    }
   @DeleteMapping("/deleteuser/{id}")
   public String deleteUser(@PathVariable int id){
       return userService.deleteUser(id);
   }
   @DeleteMapping("/deleteusers/{ids}")
   public String deleteUsers(@PathVariable List<Integer> ids){
        return userService.deleteUsers(ids);
   }
  @PutMapping("/updateuser")
   public User updateUser(@RequestBody User user){
        return userService.updateUser(user);
   }
   @PutMapping("/updateusers")
   public List<User> updateUsers(@RequestBody List<User> users){
       return userService.updateUsers(users);
   }

}
