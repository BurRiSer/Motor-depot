package service;

import domain.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> findAll() throws ServiceException;

    Driver findByUserId(Long id) throws ServiceException;

    Driver findByVehicleId(Long id) throws ServiceException;
}
