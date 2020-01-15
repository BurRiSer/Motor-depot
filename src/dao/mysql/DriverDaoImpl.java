package dao.mysql;

import dao.DaoException;
import dao.DriverDao;
import domain.Driver;
import domain.User;
import domain.Vehicle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DriverDaoImpl extends BaseDaoImpl implements DriverDao {
    @Override
    public List<Driver> readAll() throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `birth`, `users_id`, `vehicles_id` FROM `drivers`";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            List<Driver> drivers = new ArrayList<>();
            while (resultSet.next()) {
                Driver driver = new Driver();
                driver.setId(resultSet.getLong("id"));
                driver.setName(resultSet.getString("name"));
                driver.setSurname(resultSet.getString("surname"));
                driver.setBirth(new Date(resultSet.getTimestamp("birth").getTime()));
                if (!resultSet.wasNull()){
                    driver.setUser(new User());
                    driver.getUser().setId(resultSet.getLong("users_id"));
                }
                if (!resultSet.wasNull()){
                    driver.setVehicle(new Vehicle());
                    driver.getVehicle().setId(resultSet.getLong("vehicles_id"));
                }
                drivers.add(driver);
            }
            return drivers;
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
    public Driver readByVehicleId(Long vehicleId) throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `birth`, `vehicles_id` FROM `drivers` WHERE `vehicles_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, vehicleId);
            resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = new Driver();
                driver.setId(resultSet.getLong("id"));
                driver.setName(resultSet.getString("name"));
                driver.setSurname(resultSet.getString("surname"));
                driver.setBirth(new Date(resultSet.getTimestamp("birth").getTime()));
                driver.setVehicle(new Vehicle());
                driver.getVehicle().setId(vehicleId);
            }
            return driver;
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
    public Driver readByUserId(Long userId) throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `birth`, `users_id`,`vehicles_id` FROM `drivers` WHERE `users_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, userId);
            resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = new Driver();
                driver.setId(resultSet.getLong("id"));
                driver.setName(resultSet.getString("name"));
                driver.setSurname(resultSet.getString("surname"));
                driver.setBirth(new Date(resultSet.getTimestamp("birth").getTime()));
                driver.setUser(new User());
                driver.getUser().setId(userId);
                driver.setVehicle(new Vehicle());
                driver.getVehicle().setId(resultSet.getLong("vehicles_id"));
            }
            return driver;
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
    public Driver read(Long id) throws DaoException {
        String sql = "SELECT `name`, `surname`, `birth`, `vehicles_id` FROM `drivers` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Driver driver = null;
            if (resultSet.next()) {
                driver = new Driver();
                driver.setId(id);
                driver.setName(resultSet.getString("name"));
                driver.setSurname(resultSet.getString("surname"));
                driver.setBirth(new Date(resultSet.getTimestamp("birth").getTime()));
                driver.setVehicle(new Vehicle());
                driver.getVehicle().setId(id);
            }
            return driver;
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
    public Long create(Driver driver) throws DaoException {
        String sql = "INSERT INTO `drivers` (`name`, `surname`, `birth`, `users_id`, `vehicles_id`) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getSurname());
            statement.setDate(3, new java.sql.Date(driver.getBirth().getTime()));
            statement.setLong(4, driver.getUser().getId());
            statement.setLong(5, driver.getVehicle().getId());
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
    public void update(Driver driver) throws DaoException {
        String sql = "UPDATE `drivers` SET  `name` = ?, `surname` = ?, `birth` = ?, `users_id` = ?, `vehicles_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getSurname());
            statement.setDate(3, new java.sql.Date(driver.getBirth().getTime()));
            statement.setLong(4, driver.getUser().getId());
            statement.setLong(5, driver.getVehicle().getId());
            statement.setLong(6, driver.getId());
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
        String sql = "DELETE FROM `drivers` WHERE `id` = ?";
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
