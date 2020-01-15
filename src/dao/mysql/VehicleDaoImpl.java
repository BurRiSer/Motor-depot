package dao.mysql;

import dao.DaoException;
import dao.VehicleDao;
import domain.Vehicle;
import domain.VehicleType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehicleDaoImpl extends BaseDaoImpl implements VehicleDao {

    @Override
    public List<Vehicle> readAll() throws DaoException {
        String sql = "SELECT `id`, `model`, `serviceability`, `type` FROM `vehicles`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            resultSet = statement.executeQuery(sql);
            List<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getLong("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setServiceability(resultSet.getBoolean("serviceability"));
                vehicle.setType(VehicleType.values()[resultSet.getInt("type")]);
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public List<Vehicle> readByTypeAndServiceability(VehicleType vehicleType, Boolean serviceability) throws DaoException {
        String sql = "SELECT `id`, `model`, `serviceability`, `type` FROM `vehicles` WHERE `type` = ? AND `serviceability` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, vehicleType.ordinal());
            statement.setBoolean(2, serviceability);
            resultSet = statement.executeQuery();
            List<Vehicle> vehicles = new ArrayList<>();
            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(resultSet.getLong("id"));
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setServiceability(serviceability);
                vehicle.setType(vehicleType);
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Vehicle read(Long id) throws DaoException {
        String sql = "SELECT `model`, `serviceability`, `type` FROM `vehicles` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Vehicle vehicle = null;
            if (resultSet.next()) {
                vehicle = new Vehicle();
                vehicle.setId(id);
                vehicle.setModel(resultSet.getString("model"));
                vehicle.setServiceability(resultSet.getBoolean("serviceability"));
                vehicle.setType(VehicleType.values()[resultSet.getInt("type")]);
            }
            return vehicle;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public Long create(Vehicle vehicle) throws DaoException {
        String sql = "INSERT INTO `vehicles` (`model`, `serviceability`, `type`) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, vehicle.getModel());
            statement.setBoolean(2, vehicle.getServiceability());
            statement.setInt(3, vehicle.getType().ordinal());
            statement.executeUpdate();
            Long id = null;
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            return id;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
            try {
                resultSet.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void update(Vehicle vehicle) throws DaoException {
        String sql = "UPDATE `vehicles` SET `model` = ?, `serviceability` = ?, `type` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, vehicle.getModel());
            statement.setBoolean(2, vehicle.getServiceability());
            statement.setInt(3, vehicle.getType().ordinal());
            statement.setLong(4, vehicle.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        String sql = "DELETE FROM `vehicles` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
            }
        }
    }
}
