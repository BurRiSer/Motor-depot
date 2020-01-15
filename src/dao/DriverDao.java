package dao;

import domain.Driver;

import java.util.List;

public interface DriverDao extends Dao<Driver>{
    List<Driver> readAll() throws DaoException;

    Driver readByVehicleId(Long id) throws DaoException;

    Driver readByUserId(Long id) throws DaoException;
}
