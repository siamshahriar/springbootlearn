package com.example.demo.controller;

import com.example.demo.config.JwtUtils;
import com.example.demo.helper.UserNotFoundException;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    //generate token
    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found "+e.getMessage());
        }
        //authentication

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
         String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        // Authentication logic
        // This method should validate the username and password
        // and return a JWT token if successful.

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("inside authenticate method");
        }catch(DisabledException e){
            throw new Exception("User Disabled "+e.getMessage());
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }
    }
    //returns current user
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }
}
