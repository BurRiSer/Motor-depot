package service.impl;

import dao.DaoException;
import dao.VehicleDao;
import domain.Vehicle;
import domain.VehicleType;
import service.*;

import java.util.List;

public class VehicleServiceImpl extends BaseService implements VehicleService {
    private VehicleDao vehicleDao;

    public void setVehicleDao(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Vehicle findById(Long id) throws ServiceException {
        try {
            return vehicleDao.read(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vehicle> findByTypeAndServiceability(VehicleType vehicleType, Boolean serviceability) throws ServiceException {
        try {
            return vehicleDao.readByTypeAndServiceability(vehicleType, serviceability);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Vehicle> findAll() throws ServiceException {
        try {
            return vehicleDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void save(Vehicle vehicle) throws ServiceException {
        try {
            getTransaction().start();
            if(vehicle.getId() != null) {
                vehicleDao.update(vehicle);
            } else {
                    throw new VehicleNotExistsException(vehicle.getId());
            }
            getTransaction().commit();
        } catch(DaoException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw new ServiceException(e);
        } catch(ServiceException e) {
            try { getTransaction().rollback(); } catch(ServiceException e1) {}
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws ServiceException {
        try {
            vehicleDao.delete(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
