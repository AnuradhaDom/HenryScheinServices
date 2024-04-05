package com.henryschein.userservice.serviceImpl;

import org.slf4j.LoggerFactory;
import com.henryschein.userservice.model.User;
import com.henryschein.userservice.repository.UserRepository;
import com.henryschein.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(User user) {
        try {
            User userAdded = userRepository.save(user);
            LOGGER.info("User {} is saved ", user);
            return userAdded;
        }
        catch(Exception e){
            LOGGER.error("Error occurred while creating User: {}", user , e );
            throw new RuntimeException("Failed to create User");
        }
    }

    @Override
    public List<User> createUsers(List<User> users) {
        try {
           List<User> usersAdded = userRepository.saveAll(users);
            for (int i = 0; i < usersAdded.size(); i++) {
                User user = usersAdded.get(i);
                LOGGER.info("User {} is saved ", user);
            }
            return usersAdded;
        }
        catch(Exception e){
            LOGGER.error("Error occurred while creating Users: {}", users , e);
            throw new RuntimeException("Failed to create Users");
        }

    }

    @Override
    public List<User> getUsers() {
        try {
            List<User> allUsers = userRepository.findAll();
            for (int i = 0; i < allUsers.size(); i++) {
                User user = allUsers.get(i);
                LOGGER.info("successfully getUsers method executed {}", user);

            }
            return allUsers;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching Users: {}" ,e );
            throw new RuntimeException("Failed to Fetch User");
        }
    }

    @Override
    public Optional<User> getUserById(int id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            LOGGER.info("successfully getUserById method executed {}", user.getId());
            return userRepository.findById(id);
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching User by Id: {}" ,e);
            throw new RuntimeException("Failed to Fetch User by Id");
        }
    }

    @Override
    public List<User> getUserByName(String name) {
        try {
            List<User> users = userRepository.findByName(name);
            LOGGER.info("successfully getUserByName method executed {}", users);
            return users;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching Users by Name: {}" ,e);
            throw new RuntimeException("Failed to Fetch Users by Name");
        }
    }

    @Override
    public User getUserByEmailId(String emailId) {
        try {
            User user = userRepository.findByEmail(emailId);
            LOGGER.info("successfully getUserByEmailId method executed {}", user);
            return user;
        } catch (Exception e) {
            LOGGER.error("Error occurred while fetching User by EmailId: {}" ,e);
            throw new RuntimeException("Failed to Fetch User by emailId");
        }

    }

    @Override
    public String deleteUser(int id) {
        try {
            User user = userRepository.findById(id).orElse(null);
            userRepository.deleteById(id);
            LOGGER.info("User Deleted {}", user);
            return "User removed successfully!" + id;
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting User by Id: {}" ,e);
            throw new RuntimeException("Failed to Delete User by Id");
        }
    }

    @Override
    public String deleteUsers(List<Integer> ids) {
        try {
            for (int i = 0; i < ids.size(); i++) {
                User user = userRepository.findById(i).orElse(null);
                userRepository.deleteById(user.getId());
                LOGGER.info("User Deleted {}", user.getId());

            }
            return "Users Deleted successfully !";
        } catch (Exception e) {
            LOGGER.error("Error occurred while Deleting Users by Id's: {}" ,e);
            throw new RuntimeException("Failed to Delete Users by Id's");
        }
    }

    @Override
    public User updateUser(User user) {
        try {
            User existingUser = userRepository.findById(user.getId()).orElse(null);
            existingUser.setName(user.getName());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            User updatedUser = userRepository.save(existingUser);
            LOGGER.info("User Updated {}", updatedUser);
            return updatedUser;
        } catch (Exception e) {
            LOGGER.error("Error occurred while Updating User: {}" ,e);
            throw new RuntimeException("Failed to Update User");
        }
    }

    @Override
    public List<User> updateUsers(List<User> users) {
        try {
            List<User> updatedUsers = new ArrayList<>();

            for (int i = 0; i < users.size(); i++) {
                User newUserData = users.get(i);
                User existingUser = userRepository.findById(newUserData.getId()).orElse(null);
                existingUser.setName(newUserData.getName());
                existingUser.setPassword(newUserData.getPassword());
                existingUser.setEmail(newUserData.getEmail());
                User updatedUser = userRepository.save(existingUser);
                LOGGER.info("User Updated {}", updatedUsers);

                updatedUsers.add(updatedUser);
                LOGGER.info("User Updated successfully{}", updatedUsers);
            }
            return updatedUsers;

        } catch (Exception e) {
            LOGGER.error("Error occurred while Updating Users: {}" ,e);
            throw new RuntimeException("Failed to Updating Users");

        }
    }
}
