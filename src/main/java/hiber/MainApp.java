package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasya = new User("Vasya", "Vasechkin", "vasechkin@mail.io");
      User petya = new User("Petya", "Sidorov", "sidorov@mail.io");
      User olga = new User("Olga", "Petrova", "petrova@mail.io");
      User sveta = new User("Svetlana", "Ivanova", "ivanova@mail.io");

      Car volvo = new Car("Volvo", 9);
      Car bmw = new Car("BMW", 325);
      Car suzuki = new Car("Sisuki", 4);
      Car lada = new Car("Ladaa", 21014);

      userService.add(vasya.setCar(volvo).setUser(vasya));
      userService.add(petya.setCar(bmw).setUser(petya));
      userService.add(olga.setCar(suzuki).setUser(olga));
      userService.add(sveta.setCar(lada).setUser(sveta));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
