package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;

import hiber.service.CarServiceImp;
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

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru" ));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));




      //CarServiceImp carService = context.getBean(CarServiceImp.class);
//      carService.deleteAllCars();
//      carService.add(new Car("A", "1"));
//      carService.add(new Car("B", "2"));
//      carService.add(new Car("C", "3"));
//      carService.add(new Car("D", "4"));
     // userService.deleteAllUsers();
     // List<Car> cars = carService.listCars();

      User user1 = new User("User5", "Lastname5", "user5@mail.ru");
      User user2 = new User("User6", "Lastname6", "user6@mail.ru");
      Car car1 = new Car("Ford", "06");
      Car car2 = new Car("DODGE", "07");
      user1.setCar(car1);
      user2.setCar(car2);
      userService.add(user1);
      userService.add(user2);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }
      System.out.println("Найти \"Ford 06\"");
      System.out.println(userService.findOwner(car1));
      context.close();
   }
}
