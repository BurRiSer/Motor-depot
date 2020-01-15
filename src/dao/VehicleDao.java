package dao;

import domain.Vehicle;
import domain.VehicleType;

import java.util.List;

public interface VehicleDao extends Dao<Vehicle> {
    List<Vehicle> readAll() throws DaoException;

    List<Vehicle> readByTypeAndServiceability(VehicleType vehicleType, Boolean serviceability) throws DaoException;
}
