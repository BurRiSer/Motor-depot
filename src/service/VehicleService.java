package service;

import domain.Vehicle;
import domain.VehicleType;

import java.util.List;

public interface VehicleService {
    Vehicle findById(Long id) throws ServiceException;

    List<Vehicle> findByTypeAndServiceability(VehicleType vehicleType, Boolean serviceability) throws ServiceException;

    List<Vehicle> findAll() throws ServiceException;

    void save(Vehicle vehicle) throws ServiceException;

    void delete(Long id) throws ServiceException;
}
