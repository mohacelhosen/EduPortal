package com.mohacel.edu.controller;

import com.mohacel.edu.dto.CompleteUserDto;
import com.mohacel.edu.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/")
    public ResponseEntity<String> welcome(){
        String msg = "Welcome the world of REST API";
        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody CompleteUserDto completeUserDto){
        boolean isSaved = userService.registerUser(completeUserDto);
        if (isSaved){
            return new ResponseEntity<>("Save successfully..✅", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Fail to Register❌", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CompleteUserDto> getUserInfoById(@PathVariable Integer userId){
        CompleteUserDto userById = userService.findUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }
}
