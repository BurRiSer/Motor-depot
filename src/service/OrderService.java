package service;

import domain.Driver;
import domain.Order;
import domain.OrderStatus;

import java.util.List;

public interface OrderService {
    Order findById(Long id) throws ServiceException;

    List<Order> findByUserId(Long id) throws ServiceException;

    List<Order> findByStatus(OrderStatus orderStatus) throws ServiceException;

    List<Order> findByStatusAndDriverId(OrderStatus orderStatus, Long driverId) throws ServiceException;

    void save(Order order) throws ServiceException;

    void confirmDriver(Long orderId, Driver driver) throws ServiceException;

    void changeStatus(Long orderId, OrderStatus orderStatus) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
