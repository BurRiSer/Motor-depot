package service.impl;

import dao.DaoException;
import dao.OrderDao;
import domain.Driver;
import domain.Order;
import domain.OrderStatus;
import service.OrderNotExistsException;
import service.OrderService;
import service.ServiceException;

import java.util.List;

public class OrderServiceImpl extends BaseService implements OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order findById(Long id) throws ServiceException {
        try {
            return orderDao.read(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findByUserId(Long id) throws ServiceException {
        try {
            return orderDao.readByUserId(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findByStatus(OrderStatus orderStatus) throws ServiceException {
        try {
            return orderDao.readByStatus(orderStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findByStatusAndDriverId(OrderStatus orderStatus, Long driverId) throws ServiceException {
        try {
            return orderDao.readByStatusAndDriverId(orderStatus, driverId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Order order) throws ServiceException {
        try {
            getTransaction().start();
            if (order.getId() != null) {
                Order storedOrder = orderDao.read(order.getId());
                if (storedOrder != null) {
                    orderDao.update(order);
                } else {
                    throw new OrderNotExistsException(order.getId());
                }
            } else {
                Long id = orderDao.create(order);
                order.setId(id);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void confirmDriver(Long orderId, Driver driver) throws ServiceException {
        try {
            getTransaction().start();
            Order order = orderDao.read(orderId);
            if (order != null && driver != null) {
                order.setDriver(driver);
                orderDao.update(order);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void changeStatus(Long orderId, OrderStatus orderStatus) throws ServiceException {
        try {
            getTransaction().start();
            Order order = orderDao.read(orderId);
            if (order != null) {
                order.setStatus(orderStatus);
                orderDao.update(order);
            }
            getTransaction().commit();
        } catch (DaoException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw new ServiceException(e);
        } catch (ServiceException e) {
            try {
                getTransaction().rollback();
            } catch (ServiceException e1) {
            }
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            orderDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
