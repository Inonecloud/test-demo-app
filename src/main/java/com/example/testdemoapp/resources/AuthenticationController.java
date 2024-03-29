package com.example.testdemoapp.resources;

import com.example.testdemoapp.resources.vm.LoginUser;
import com.example.testdemoapp.security.DomainUserDetailsService;
import com.example.testdemoapp.security.jwt.TokenProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
public class AuthenticationController {
    private AuthenticationManager authenticationManager;
    private DomainUserDetailsService userDetailsService;
    private TokenProvider tokenProvider;

    public AuthenticationController(AuthenticationManager authenticationManager, DomainUserDetailsService userDetailsService, TokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@Valid @RequestBody LoginUser loginUser){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword());
        authenticationManager.authenticate(authenticationToken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getUsername());
        final String jwt = tokenProvider.generateToken(userDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(jwt, httpHeaders, HttpStatus.OK);
    }
}
