package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE users ("
                + "id INT(64) NOT NULL AUTO_INCREMENT,"
                + "name VARCHAR(50),"
                + "lastName VARCHAR(50),"
                + "age INT(3),"
                + "primary KEY(id))";
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            try {
                session.createSQLQuery(sql).executeUpdate();
                transaction.commit();
                System.out.println("Table successfully created...");
            } catch (Exception s) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("The table already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE users";
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            try {
                session.createSQLQuery(sql).executeUpdate();
                transaction.commit();
                System.out.println("Table successfully deleted...");
            } catch (Exception s) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.out.println("The table does not exist");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("The user" + " " + name + " " + "added...");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            System.out.println("User was deleted...");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = getSessionFactory().openSession()) {
            users = (List<User>)  session.createQuery("From User").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            List<User> users = (List<User>)  session.createQuery("From User").list();
            transaction = session.beginTransaction();
            for(User user : users) {
                session.delete(user);
            }
            transaction.commit();
            System.out.println("Table successfully cleaned...");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
