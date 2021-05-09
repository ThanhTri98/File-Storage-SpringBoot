package com.api.filestorage.services;

import java.util.List;
import java.util.stream.Collectors;

import com.api.filestorage.dto.RoleDTO;
import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(u -> {
            return new UserDTO().toDTO(u);
        }).collect(Collectors.toList());
    }

    public UserDTO findByUsername(String userName) {
        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null)
            return null;
        return new UserDTO().toDTO(userEntity);
    }

    public UserDTO findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null)
            return null;
        return new UserDTO().toDTO(userEntity);
    }
    
    public void register(UserDTO user) {
        RoleDTO role = new RoleDTO(1, "USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = new UserEntity().toEntity(user);
        userRepository.save(userEntity);
    }

}
