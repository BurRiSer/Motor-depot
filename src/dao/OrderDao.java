package dao;

import domain.Order;
import domain.OrderStatus;

import java.util.List;

public interface OrderDao extends Dao<Order> {
    List<Order> readByUserId(Long id) throws DaoException;

    List<Order> readByStatus(OrderStatus orderStatus) throws DaoException;

    List<Order> readByStatusAndDriverId(OrderStatus orderStatus, Long driverId) throws DaoException;
}
