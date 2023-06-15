package com.mohacel.edu.service;

import com.mohacel.edu.dto.CompleteUserDto;
import com.mohacel.edu.model.CompleteUserEntity;

import java.util.List;

public interface IUserService {
    public boolean registerUser(CompleteUserDto completeUserDto);
    public CompleteUserDto findUserById(Integer userId);
    public boolean deleteUserById(Integer userId);
    public String updateUserInfo(Integer userId, CompleteUserDto completeUserDto);
    public List<CompleteUserDto> getAllUser();
}
