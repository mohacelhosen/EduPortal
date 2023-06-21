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

    @DeleteMapping("/user/{userId}")
    public  ResponseEntity<String> deleteUserById(@PathVariable Integer userId){
        boolean isUserDelete = userService.deleteUserById(userId);
        String msg = "Delete Fail";
        if (isUserDelete !=false){
            msg = "Delete Successful";
        }

        return  new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<String> updateUserInfo(@PathVariable Integer userId,@RequestBody CompleteUserDto completeUserDto){
        String updateMessage = userService.updateUserInfo(userId, completeUserDto);
        return  new ResponseEntity<>(updateMessage, HttpStatus.OK);
    }

}
