package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Currency;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public void deleteAllUsers() {
      List<User> users = listUsers();
      for (User user: users) {
         sessionFactory.getCurrentSession().delete(user);
      }
   }

   @Override
   public User findOwner(Car car) {
      TypedQuery<User> findCarQuery = sessionFactory.getCurrentSession().createQuery("from User user where user.car.name = :model and user.car.series = :series");
      findCarQuery.setParameter("model", car.getName())
              .setParameter("series", car.getSeries());
//      List<Car> findCarList = findCarQuery.getResultList();
//      if (!findCarList.isEmpty()) {
//         Car findCar = findCarList.get(0);
//         List<User> ListUser = listUsers();
//         User FindUser = ListUser.stream()
//                 .filter(user -> user.getCar().equals(findCar))
//                 .findAny()
//                 .orElse(null);
//         return FindUser;
//      }
      return findCarQuery.setMaxResults(1).getSingleResult();
   }
}