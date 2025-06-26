package com.learnwithiftekhar.spring_jwt_demo.controller;

import com.learnwithiftekhar.spring_jwt_demo.model.User;
import com.learnwithiftekhar.spring_jwt_demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-all-users")
    public ResponseEntity<?> getAllUsers() {
        List<User> userList = userService.getAllUsers();

        if(!userList.isEmpty()) {
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
