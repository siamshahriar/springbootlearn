package com.example.demo;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.UserRole;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Demo1Application implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application started successfully!");

//        User user = new User();
//
//        user.setFirstName("Siam");
//        user.setLastName("Shahriar");
//        user.setUsername("siam");
//        user.setPassword("12345");
//        user.setEmail("siam@gmail.com");
//        user.setProfile("default.png");
//
//        Role role1 = new Role();
//        role1.setRoleId(44L);
//        role1.setRoleName("ADMIN");
//
//        Set<UserRole> userRoleSet = new HashSet<>();
//        UserRole userRole =  new UserRole();
//        userRole.setRole(role1);
//        userRole.setUser(user);
//
//        userRoleSet.add(userRole);
//
//        User user1 = this.userService.createUser(user, userRoleSet);
//        System.out.println(user1.getUsername());



    }
}
