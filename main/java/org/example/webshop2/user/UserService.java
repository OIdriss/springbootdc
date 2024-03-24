package org.example.webshop2.user;

import org.example.webshop2.models.CustomUser;
import org.example.webshop2.models.Product;
import org.example.webshop2.product.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<UserDTO> getAllUsers() {
        List<CustomUser> customUsers = userRepository.findAll();
        return convertToDTOs(customUsers);
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

    public UserDTO getUser(Long userID) {
        CustomUser user = userRepository.findById(userID).orElse(null);
        if (user == null) {
            throw new IllegalStateException("student with id doesn't exist, id: "+ userID);
        }
        return convertToDTO(user);
    }

    private List<UserDTO> convertToDTOs(List<CustomUser> customUsers) {
        List<UserDTO> dtos = new ArrayList<>();
        for (CustomUser customUser : customUsers) {
            dtos.add(convertToDTO(customUser));
        }
        return dtos;
    }

    public UserDTO convertToDTO(CustomUser user){
        UserDTO userDTO = new UserDTO(user.getName(), user.getLastName(), user.getEmailAddress(), user.getPassword());
        return userDTO;
    }


}
