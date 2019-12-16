package com.example.testdemoapp.resources;

import com.example.testdemoapp.resources.vm.ManagedUser;
import com.example.testdemoapp.service.UserService;
import com.example.testdemoapp.service.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserResources {
    private UserService userService;

    public UserResources(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody ManagedUser managedUser) {
        if (!passwordChecker(managedUser.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        Boolean isCreated = userService.createUser(managedUser, managedUser.getPassword());
        if (!isCreated)
            throw new RuntimeException("User Already Exists");
    }

    @GetMapping("/all_users")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<List<UserDto>>(userService.findAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.LOCKED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUserById(@RequestParam Long id){
        userService.deleteUser(id);
    }

    private boolean passwordChecker(String password) {
        return password.length() >= ManagedUser.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUser.PASSWORD_MAX_LENGTH;
    }
}
