package pl.qcineq;

import pl.qcineq.BCrypt.BCrypt;
import pl.qcineq.DAO.UserDao;
import pl.qcineq.entity.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        User kuba = new User();
        kuba.setPassword("Pass1234#");
        kuba.setUserName("Kuba123");
        kuba.setEmail("kuba@gmail.com");
        userDao.create(kuba);

        System.out.println("\nuser with id " + kuba.getId()+ ":");
        System.out.println(userDao.read(kuba.getId()));
        System.out.println("non-existing user:");
        System.out.println(userDao.read(Long.MAX_VALUE));

        User user8 = userDao.read(8);
        System.out.println("\nemail before change: " +user8.getEmail());
        user8.setEmail("changedmail@gmail.com");
        userDao.update(user8);
        System.out.println("after change: " + userDao.read(8).getEmail());

        System.out.println("\n password: ");
        System.out.println(kuba.getPassword());
        System.out.println(userDao.read(kuba.getId()).getPassword());
        if(BCrypt.checkpw(kuba.getPassword(),userDao.read(kuba.getId()).getPassword())){
            System.out.println("password matches");
        } else {
            System.out.println("wrong password");
        }

        System.out.println("\n all users:");
        User[] userArray = userDao.findAll();
        for(User k : userArray){
            System.out.println(k);
        }
        userDao.delete(kuba.getId());
        System.out.println("all users after last deleted:");
        User[] userArray2 = userDao.findAll();
        for(User k : userArray2){
            System.out.println(k);
        }
    }
}
