package model.service;

import mapper.UserMapper;
import model.User;
import model.UserDao;
import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();
    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = userMapper.fromCreateUserDtoToUser(createUserDto);
        userDao.save(user);
        UserResponseDto userResponseDto = userMapper.fromUserToUserResponseDto(user);
        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        //using map to convert object of user to userResponseDto
        List<User> users = userDao.findAll();
        List<UserResponseDto> userResponseDtos= new ArrayList<>();
        for (User user:users){
            UserResponseDto userResponseDto = new UserResponseDto(
                    user.getUuid(),
                    user.getName(),
                    user.getEmail(),
                    user.getProfile()
            );
            userResponseDtos.add(userResponseDto);
            return userResponseDtos;
        }
        return userResponseDtos;
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto) {
        User user = userDao.findAll()
                .stream()
                .filter(u -> u.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (updateRequestDto.name() != null && !updateRequestDto.name().isEmpty()) {
            user.setName(updateRequestDto.name());
        }
        if (updateRequestDto.email() != null && !updateRequestDto.email().isEmpty()) {
            user.setEmail(updateRequestDto.email());
        }
        if (updateRequestDto.password() != null && !updateRequestDto.password().isEmpty()) {
            user.setPassword(updateRequestDto.password());
        }
        if (updateRequestDto.profile() != null && !updateRequestDto.profile().isEmpty()) {
            user.setProfile(updateRequestDto.profile());
        }
        userDao.update(user);
        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public int deleteUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        userDao.remove(user);
        return 1;
    }

    @Override
    public List<UserResponseDto> searchUserByName(String name) {
        return userDao.findAll()
                .stream()
                .filter(user -> user.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .map(userMapper::fromUserToUserResponseDto)
                .toList();
    }
}
