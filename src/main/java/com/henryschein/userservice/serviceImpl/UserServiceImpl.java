package com.henryschein.userservice.serviceImpl;

import com.henryschein.userservice.constant.UserConstants;
import com.henryschein.userservice.customeException.CustomException;
import org.slf4j.LoggerFactory;
import com.henryschein.userservice.model.User;
import com.henryschein.userservice.repository.UserRepository;
import com.henryschein.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    
    
    public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
    public User createUser(User user) {
        try {
                if (user.getUserName() == null || user.getUserName().isEmpty()) {
                    String errorMessage = UserConstants.NAME_REQUIRED_MSG;
                    LOGGER.info(errorMessage);
                    throw new CustomException(errorMessage);
                }
                // Similarly, perform other validations

            return userRepository.save(user);
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
    public Optional<User>  getUser(String name) {
        try {
            Optional<User> user = userRepository.findByUserName(name);
            LOGGER.info("successfully getUserByName method executed {}", user);
            
            return user;
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
            for (Integer id : ids) {
            	
                User user = userRepository.findById(id).orElse(null);
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
            existingUser.setUserName(user.getUserName());
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
                existingUser.setUserName(newUserData.getUserName());
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
