package com.api.filestorage.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.api.filestorage.dto.RoleDTO;
import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.entities.UserEntity;
import com.api.filestorage.repository.UserRepository;
import com.api.filestorage.security.UserDetailsImpl;
import com.api.filestorage.security.jwt.JwtUtils;
import com.api.filestorage.security.payload.request.LoginRequest;
import com.api.filestorage.security.payload.response.JwtResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;
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

    public JwtResponse signIn(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Set<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toSet());

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getEmail(), userDetails.getFull_name(),
                userDetails.getIs_active(), roles);
    }

    public void signUp(UserDTO user) {
        RoleDTO role = new RoleDTO(1, "USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity userEntity = new UserEntity().toEntity(user);
        userRepository.save(userEntity);
    }

}
