package entity;

import java.sql.SQLException;

public class MainDao {
    public static void main(String[] args) throws SQLException {
     User user = new User("Łubudubu", "łubudubu@google.pl", "łuppup345");
//        User user1 = new User("MartaWu", "Mm@yahoo.pl", "qwerty345");
//        User user2 = new User("AartaFu", "Aa@coderslab.pl", "qwerty678");
//        User user3 = new User("PsiuGnu", "psiugnu@outlook.pl", "qwut345");
//        User user4 = new User("Tatanka", "Tatanka@wp.pl", "abc123");
//        DbUtil.getConnection();
       UserDao.create(user);
//       UserDao.create(user1);
//       UserDao.create(user2);
//       UserDao.create(user3);
//       UserDao.create(user4);
        //UserDao.read(7); do mojej nieudanej metody :-(
//        User read = userDao.read(5);
//        System.out.println(read);
        UserDao userDao = new UserDao();
//        User userToUpdate = userDao.read(10);
//        userToUpdate.setEmail("Kristoff@frozen.com");
//        userToUpdate.setUserName("Kristek");
//        userToUpdate.setPassword("lodylody!@#8");
//        userDao.update(userToUpdate);

//        UserDao.delete();
        UserDao.findAll();
    }
}


