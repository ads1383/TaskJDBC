package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;



public class Main {
    public static void main(String[] args) {
        UserService userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("John", "Morgan", (byte) 46);
        userServiceImpl.saveUser("Donald", "Trump", (byte) 82);
        userServiceImpl.saveUser("Steve", "Jobs", (byte) 57);
        userServiceImpl.saveUser("Lionel", "Messi", (byte) 35);
        userServiceImpl.getAllUsers().stream().forEach(System.out::println);
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
