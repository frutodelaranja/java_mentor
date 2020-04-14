import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        User newUser = new User();
        String artifact = "opo";
        newUser.setName(artifact);
        newUser.setLogin(artifact + "@mail");
        newUser.setPassword(artifact + "777");
        System.out.println(newUser.toString());
        UserService service = UserService.getInstance();
//        service.deleteUser("VAeiA@mail");
        System.out.println(!service.thisUserExists(newUser));
        if (service.addUser(newUser)) {
            System.out.println("Пользователь успешно добавлен");
        } else {
            System.out.println("Придумай что-нибудь другое");
        }
//        System.out.println("UserID RR = " + service.getUserIdByName("tt"));

        AtomicInteger i = new AtomicInteger(1);
        service.getAllUsers().forEach(user -> System.out.println(String.valueOf(i.getAndIncrement()) + user));
        System.out.println("***********\n");
//        User secondUser = service.getUser(newUser);
//        System.out.println(secondUser);
//        service.updateUser("opo@mail", newUser);
//        System.out.println("Статус обновления пользователя: " + newUser + " = " + service.updateUser("pop@mail", newUser));
        System.out.println("***********\n");
        service.getAllUsers().forEach(user -> System.out.println(String.valueOf(i.getAndIncrement()) + user));
//        System.out.println("Статус удаления: " + service.deleteUser("opo@mail"));
        System.out.println("***********\n");
        service.getAllUsers().forEach(user -> System.out.println(String.valueOf(i.getAndIncrement()) + user));
    }
}
