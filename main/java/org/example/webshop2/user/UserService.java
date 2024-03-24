package org.example.webshop2.user;

import org.example.webshop2.models.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomUser customUser = userRepository.findByEmailAddress(email);

        return new User(email, customUser.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE USER")));
    }

    public List<CustomUser> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUser(UserDTO userDTO) {
        CustomUser customUser = new CustomUser(userDTO.name(), userDTO.lastName(), userDTO.emailAddress(), userDTO.password());
        Optional<CustomUser> userOptional = userRepository.findUserByEmail(customUser.getEmailAddress());
        if (userOptional.isPresent()){
            throw new IllegalStateException("Email taken");
        }
        userRepository.save(customUser);
    }


    public void deleteUser(Long userId) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ userId);
        }
        userRepository.deleteById(userId);
    }

    public void updateUser(Long userId, UserDTO userDTO) {
        boolean userExists = userRepository.existsById(userId);
        if (!userExists) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ userId);
        }
        else {
            CustomUser customUser = userRepository.getById(userId);
            if (isNotValidUpdate(userDTO)) {
                throw new IllegalStateException("Email is taken ");
            }
            customUser.setName(userDTO.name());
            customUser.setLastName(userDTO.lastName());
            customUser.setEmailAddress(userDTO.emailAddress());
            customUser.setPassword(userDTO.password());
            userRepository.save(customUser);
        }
    }

    public boolean isNotValidUpdate(UserDTO userDTO) {
        Optional<CustomUser> userOptional = userRepository.findUserByEmail(userDTO.emailAddress());
        return userOptional.isPresent();
    }

    public Optional<CustomUser> getUser(Long userID) {
        boolean userExists = userRepository.existsById(userID);
        if (!userExists) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ userID);
        }
        return userRepository.findById(userID);
    }
}
