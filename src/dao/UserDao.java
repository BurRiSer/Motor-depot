package dao;

import domain.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    List<User> readAll() throws DaoException;

    User readByLogin(String login) throws DaoException;

    User readByLoginAndPassword(String login, String password) throws DaoException;

    boolean isUserInOrders(Long id) throws DaoException;
}
