package factory;

import dao.DriverDao;
import dao.OrderDao;
import dao.UserDao;
import dao.VehicleDao;
import dao.mysql.DriverDaoImpl;
import dao.mysql.OrderDaoImpl;
import dao.mysql.UserDaoImpl;
import dao.mysql.VehicleDaoImpl;
import service.*;
import service.impl.*;
import pool.ConnectionPool;
import pool.PoolException;

import java.sql.Connection;

public class ServiceFactoryImpl implements ServiceFactory {
    private Connection connection;

    @Override
    public UserService getUserService() throws FactoryException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setDefaultPassword("1234");
        userService.setTransaction(getTransaction());
        userService.setUserDao(getUserDao());
        return userService;
    }

    @Override
    public VehicleService getVehicleService() throws FactoryException {
        VehicleServiceImpl vehicleService = new VehicleServiceImpl();
        vehicleService.setTransaction(getTransaction());
        vehicleService.setVehicleDao(getVehicleDao());
        return vehicleService;
    }

    @Override
    public DriverService getDriverService() throws FactoryException {
        DriverServiceImpl driverService = new DriverServiceImpl();
        driverService.setTransaction(getTransaction());
        driverService.setDriverDao(getDriverDao());
        return driverService;
    }

    @Override
    public OrderService getOrderService() throws FactoryException {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setTransaction(getTransaction());
        orderService.setOrderDao(getOrderDao());
        return orderService;
    }

    @Override
    public UserDao getUserDao() throws FactoryException {
        UserDaoImpl userDao = new UserDaoImpl();
        userDao.setConnection(getConnection());
        return userDao;
    }

    @Override
    public VehicleDao getVehicleDao() throws FactoryException {
        VehicleDaoImpl vehicleDao = new VehicleDaoImpl();
        vehicleDao.setConnection(getConnection());
        return vehicleDao;
    }

    @Override
    public DriverDao getDriverDao() throws FactoryException {
        DriverDaoImpl driverDao = new DriverDaoImpl();
        driverDao.setConnection(getConnection());
        return driverDao;
    }

    @Override
    public OrderDao getOrderDao() throws FactoryException {
        OrderDaoImpl orderDao = new OrderDaoImpl();
        orderDao.setConnection(getConnection());
        return orderDao;
    }

    @Override
    public Transaction getTransaction() throws FactoryException {
        TransactionImpl transaction = new TransactionImpl();
        transaction.setConnection(getConnection());
        return transaction;
    }

    @Override
    public Connection getConnection() throws FactoryException {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (PoolException e) {
            throw new FactoryException(e);
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            connection.close();
            connection = null;
        } catch (Exception e) {
        }
    }
}
