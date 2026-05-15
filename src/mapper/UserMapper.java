package mapper;

import model.User;
import model.dto.CreateUserDto;
import model.dto.UserResponseDto;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromCreateUserDtoToUser(CreateUserDto createUserDto){
        return new User(new Random().nextInt(999999),
                UUID.randomUUID().toString(),
                createUserDto.name(),
                createUserDto.email(),
                createUserDto.password(),
                "https://i.pinimg.com/736x/78/84/6b/78846b528d0d1f4202c178111d6a4d34.jpg");
    }
    public UserResponseDto fromUserToUserResponseDto(User user){
        return new UserResponseDto(
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfile()
        );
    }
}
