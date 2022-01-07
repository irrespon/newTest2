package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    Session session = Util.getSessionFactory().openSession();
    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("Create table " +
                    User.class.getSimpleName() +
                    " (id int(10) not null auto_increment, " +
                    " name Varchar(50), " +
                    " lastname Varchar(50), " +
                    " age int not null, " +
                    " primary key (id))").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("drop table " + User.class.getSimpleName()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class,id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try {
            transaction = session.beginTransaction();
            list = session.createQuery("FROM " + User.class.getSimpleName()).list();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }
        System.out.println(list);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE from " + User.class.getSimpleName()).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            transaction.rollback();
        }
    }
}
