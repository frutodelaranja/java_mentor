package service;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServiceTwo {
    private static ServiceTwo serviceTwo;
    private List<User> users;

    public static ServiceTwo getInstance(){
        if (serviceTwo == null) {
            serviceTwo = new ServiceTwo();
        }
        return serviceTwo;
    }
    private ServiceTwo(){
        users = new ArrayList<>();
    }
    public void add(User user) {
        users.add(user);
    }
    public List<User> getAllUsers(){
        return users;
    }

    public List<String> list() {
        return users.stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }
}
