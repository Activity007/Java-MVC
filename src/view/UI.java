package view;

import controller.UserController;
import model.User;
import model.UserDao;
import model.dto.CreateUserDto;
import model.dto.UpdateRequestDto;
import model.dto.UserResponseDto;
import model.service.UserService;
import model.service.UserServiceImpl;
import utils.ApiResponseTemplate;

import java.util.List;
import java.util.Scanner;

public class UI {

    private final static UserController usercontroller = new UserController();
    private final static UserService userService = new UserServiceImpl();
    private final static UserDao userDao = new UserDao();

    private static void thumbnail(){
        System.out.println("""
                ============= User Management System ============
                1. Create User
                2. Search User By UUID
                3. Search User By Name
                4. Delete User By UUID
                5. Update User By UUID
                6. List all users
                7. Exit
                """);
    }
    private static int insertOption(){
        System.out.print("Insert your option : ");
        return new Scanner(System.in).nextInt();
    }
    public static void getRendered(){
        while (true){
            thumbnail();
            switch (insertOption()){
                case 1 -> {
                    System.out.println("====== Create user =======");
                    System.out.print("[+] Insert name : ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert email : ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert password : ");
                    String password = new Scanner(System.in).nextLine();

                    CreateUserDto createUserDto = new CreateUserDto(name, email, password);
                    ApiResponseTemplate<UserResponseDto> createdUser =
                            usercontroller.createUser(createUserDto);
                    System.out.println(createdUser);
                    TableView.UserViewTable(userDao.findAll());
                }
                case 2 -> {
                    try {
                        System.out.print("[+] Enter UUID : ");
                        String uuid = new Scanner(System.in).nextLine();
                        UserResponseDto user = userService.getUserByUuid(uuid);
                        System.out.println(user);
                        List<User> users = userDao.findAll()
                                .stream()
                                .filter(u -> u.getUuid().equals(uuid))
                                .toList();
                        TableView.UserViewTable(users);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 3 -> {
                    try {
                        System.out.print("[+] Enter name : ");
                        String name = new Scanner(System.in).nextLine();

                        List<User> users = userDao.findAll()
                                .stream()
                                .filter(user -> user.getName() != null &&
                                        user.getName()
                                                .toLowerCase()
                                                .contains(name.toLowerCase()))
                                .toList();
                        TableView.UserViewTable(users);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("[+] Enter UUID : ");
                        String uuid = new Scanner(System.in).nextLine();
                        ApiResponseTemplate<Integer> deletedUser =
                                usercontroller.deleteUserByUuid(uuid);
                        System.out.println(deletedUser);
                        TableView.UserViewTable(userDao.findAll());

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 5 -> {
                    try {
                        System.out.print("[+] Enter UUID : ");
                        String uuid = new Scanner(System.in).nextLine();
                        System.out.print("[+] Insert new name : ");
                        String name = new Scanner(System.in).nextLine();
                        System.out.print("[+] Insert new email : ");
                        String email = new Scanner(System.in).nextLine();
                        System.out.print("[+] Insert new password : ");
                        String password = new Scanner(System.in).nextLine();
                        System.out.print("[+] Insert new profile : ");
                        String profile = new Scanner(System.in).nextLine();

                        UpdateRequestDto updateRequestDto =
                                new UpdateRequestDto(name, email, password, profile);

                        ApiResponseTemplate<UserResponseDto> updatedUser =
                                usercontroller.updateUserByUuid(uuid, updateRequestDto);
                        System.out.println(updatedUser);
                        TableView.UserViewTable(userDao.findAll());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 6 -> {
                    System.out.println(usercontroller.getAllUsers());

                    TableView.UserViewTable(userDao.findAll());
                }
                case 7 -> {
                    System.out.println("System closed ....");
                    try {
                        Thread.sleep(100);
                    } catch (Exception ignore) {}
                    return;
                }
                default -> System.out.println("No Invalid Option");
            }
        }
    }
}