package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.TokenProvider;
import com.example.demo.model.*;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;

@CrossOrigin(origins = "*", maxAge = 3600)  
@RestController  
@RequestMapping("/users") 
public class UserApi {

    @Autowired
    private AuthenticationManager authenticationManager;  

    @Autowired
    private TokenProvider jwtTokenUtil;  

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication); 
        final String token = jwtTokenUtil.generateToken(authentication);  
        return ResponseEntity.ok(new AuthToken(token));  
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public User saveUser(@RequestBody UserDto user){
        return userService.save(user); 
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/userping", method = RequestMethod.GET)
    public String userPing(){
        return "User ressource"; 
    }
       
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value="/adminping", method = RequestMethod.GET)
    public String adminPing(){
        return "Admin ressource";  
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
    	return userService.findOne(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getUsers() {
    	return this.userService.findAll();  
    }
    
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return this.userService.updateUser(id, updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{username}")
    public String deleteUser(@PathVariable String username) {
        User user = userService.findOne(username);
        return userService.supprimerUser(user);
    }
    





}
