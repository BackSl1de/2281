package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Vasya", "Pupkin1", "P1", new Car("lada", 1));
        userService.add(user1);
        User user2 = new User("Petya", "Pupkin2", "P2", new Car("mazda", 2));
        userService.add(user2);
        User user3 = new User("Masha", "Pupkin3", "P3", new Car("tayota", 3));
        userService.add(user3);
        User user4 = new User("Misha", "Pupkin4", "P4", new Car("hueta", 4));
        userService.add(user4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        List<User> filter1 = userService.getUser("mazda", 2);
        if (!filter1.isEmpty()) {
            for (User user : filter1) {
                System.out.println("Id = " + user.getId());
                System.out.println("First Name = " + user.getFirstName());
                System.out.println("Last Name = " + user.getLastName());
                System.out.println("Email = " + user.getEmail());
            }
        } else {
            System.out.println("Никого нет дома");
        }
        context.close();
    }
}
