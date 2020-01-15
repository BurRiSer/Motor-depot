package service.impl;

import dao.DaoException;
import dao.DriverDao;
import domain.Driver;
import service.DriverService;
import service.ServiceException;

import java.util.List;

public class DriverServiceImpl extends BaseService implements DriverService {
    private DriverDao driverDao;

    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Override
    public List<Driver> findAll() throws ServiceException {
        try {
            return driverDao.readAll();
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Driver findByUserId(Long id) throws ServiceException {
        try {
            return driverDao.readByUserId(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Driver findByVehicleId(Long id) throws ServiceException{
        try {
            return driverDao.readByVehicleId(id);
        } catch(DaoException e) {
            throw new ServiceException(e);
        }
    }
}
