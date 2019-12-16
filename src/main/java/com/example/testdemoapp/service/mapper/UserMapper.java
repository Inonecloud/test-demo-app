package com.example.testdemoapp.service.mapper;

import com.example.testdemoapp.domain.Authority;
import com.example.testdemoapp.domain.User;
import com.example.testdemoapp.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public List<UserDto> toDto(List<User> users){
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<User> toEntity(List<UserDto> users){
        return users.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    public User toEntity(UserDto dto) {
        if (dto == null)
            return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        Set<Authority> authorities = this.authoritiesFromString(dto.getAuthorities());
        user.setAuthorities(authorities);
        return user;
    }

    private Set<Authority> authoritiesFromString(Set<String> authoritiesAsString) {
        Set<Authority> authorities = new HashSet<>();
        if (authoritiesAsString!=null){
            authorities = authoritiesAsString.stream()
                    .map(string->{
                        Authority authority = new Authority();
                        authority.setName(string);
                        return authority;
                    }).collect(Collectors.toSet());
        }
        return authorities;
    }

    public UserDto toDto(User user){
        return new UserDto(user);
    }


}
