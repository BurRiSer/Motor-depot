package factory;

import dao.DriverDao;
import dao.OrderDao;
import dao.UserDao;
import dao.VehicleDao;
import service.*;

import java.sql.Connection;

public interface ServiceFactory extends AutoCloseable {
    UserService getUserService() throws FactoryException;
    VehicleService getVehicleService() throws FactoryException;
    DriverService getDriverService() throws FactoryException;
    OrderService getOrderService() throws FactoryException;

    UserDao getUserDao() throws FactoryException;
    VehicleDao getVehicleDao() throws FactoryException;
    DriverDao getDriverDao() throws FactoryException;
    OrderDao getOrderDao() throws FactoryException;

    Transaction getTransaction() throws FactoryException;

    Connection getConnection() throws FactoryException;
}
