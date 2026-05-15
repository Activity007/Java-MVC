package controller;

import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;
import model.service.UserService;
import model.service.UserServiceImpl;
import utils.ApiResponseTemplate;

import java.time.LocalDate;
import java.util.List;


public class UserController {
    private final UserService userService = new UserServiceImpl();
    public ApiResponseTemplate<UserResponseDto> createUser(CreateUserDto createUserDto){
        return ApiResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .timeStamp(LocalDate.now())
                .data(userService.createUser(createUserDto))
                .build();
    }
    public ApiResponseTemplate<List<UserResponseDto>> getAllUsers(){
        return new ApiResponseTemplate<>(
                200,
                "Get all users Successfully ",
                LocalDate.now(),
                userService.getAllUsers()
        );
    }
    public ApiResponseTemplate<UserResponseDto> getUserByUuid(String uuid){
        return ApiResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .timeStamp(LocalDate.now())
                .data(userService.getUserByUuid(uuid))
                .build();
    }
    public ApiResponseTemplate<UserResponseDto> updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto){
        return ApiResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Update user successfully")
                .timeStamp(LocalDate.now())
                .data(userService.updateUserByUuid(uuid,updateRequestDto))
                .build();
    }
    public ApiResponseTemplate<Integer> deleteUserByUuid(String uuid) {
        return ApiResponseTemplate.<Integer>builder()
                .status(200)
                .message("Delete user successfully")
                .timeStamp(LocalDate.now())
                .data(userService.deleteUserByUuid(uuid))
                .build();
    }
    public ApiResponseTemplate<List<UserResponseDto>>searchUserByName(String name){
        return new ApiResponseTemplate<>(
                200,
                "Search user successfully",
                LocalDate.now(),
                userService.searchUserByName(name)
        );
    }
}