package com.api.filestorage.security;

import com.api.filestorage.dto.UserDTO;
import com.api.filestorage.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findByUsername(username);
        if (userDTO == null)
            throw new UsernameNotFoundException("Username not found!!!");
        return UserDetailsImpl.build(userDTO);
    }

}
