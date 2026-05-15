package model.service;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    UserResponseDto createUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    List<UserResponseDto> searchUserByName(String name);
}
