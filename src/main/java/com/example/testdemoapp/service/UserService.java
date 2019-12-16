package com.example.testdemoapp.service;

import com.example.testdemoapp.domain.Authority;
import com.example.testdemoapp.domain.User;
import com.example.testdemoapp.repository.AuthorityRepository;
import com.example.testdemoapp.repository.UserRepository;
import com.example.testdemoapp.security.AuthoritiesConstants;
import com.example.testdemoapp.service.dto.UserDto;
import com.example.testdemoapp.service.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepo;
    private UserMapper userMapper;
    private AuthorityRepository authorityRepo;

    public UserService(UserRepository userRepo, UserMapper userMapper, AuthorityRepository authorityRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.authorityRepo = authorityRepo;
    }

    public Boolean createUser(UserDto userDto, String password) {
        User user = userMapper.toEntity(userDto);

        if (userRepo.findByUsernameAndEmail(user.getUsername(), user.getEmail()) != null) {
            return false;
        }

        user.setPassword(new BCryptPasswordEncoder().encode(password));
        Set<Authority> authorities = new HashSet<>();
        authorityRepo.findById(AuthoritiesConstants.USER.name()).ifPresent(authorities::add);
        user.setAuthorities(authorities);
        userRepo.save(user);
        return true;
    }

    public List<UserDto> findAllUsers() {
        return userMapper.toDto(userRepo.findAll());
    }

    public void deleteUser(Long id) {
        User user = userRepo.findUserById(id);
        if (user != null) {
            userRepo.delete(user);
        }
    }
}
