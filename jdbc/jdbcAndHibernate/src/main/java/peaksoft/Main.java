package peaksoft;

import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        System.out.println(Util.getConnection());
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.dropUsersTable();

        userService.saveUser("Yryskeldi","Alymbekov",(byte) 16);

        userService.removeUserById(1);

        userService.getAllUsers();

        userService.cleanUsersTable();
    }
}
