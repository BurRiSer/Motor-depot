package dao.mysql;

import dao.DaoException;
import dao.OrderDao;
import domain.*;
import domain.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    @Override
    public List<Order> readByUserId(Long id) throws DaoException {
        String sql = "SELECT `id`,`date`, `departure_point`, `arrival_point`, `vehicle_type`, `status`, `users_id` FROM `orders` WHERE `users_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDate(new Date(resultSet.getTimestamp("date").getTime()));
                order.setDeparturePoint(resultSet.getString("departure_point"));
                order.setArrivalPoint(resultSet.getString("arrival_point"));
                order.setVehicleType(VehicleType.values()[resultSet.getInt("vehicle_type")]);
                order.setStatus(OrderStatus.values()[resultSet.getInt("status")]);
                if (!resultSet.wasNull()) {
                    order.setUser(new User());
                    order.getUser().setId(id);
                }
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByStatus(OrderStatus orderStatus) throws DaoException {
        String sql = "SELECT `id`,`date`, `departure_point`, `arrival_point`, `vehicle_type`, `status`, `users_id` FROM `orders` WHERE `status` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, orderStatus.ordinal());
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDate(new Date(resultSet.getTimestamp("date").getTime()));
                order.setDeparturePoint(resultSet.getString("departure_point"));
                order.setArrivalPoint(resultSet.getString("arrival_point"));
                order.setVehicleType(VehicleType.values()[resultSet.getInt("vehicle_type")]);
                order.setStatus(orderStatus);
                if (!resultSet.wasNull()) {
                    order.setUser(new User());
                    order.getUser().setId(resultSet.getLong("users_id"));
                }
                orders.add(order);
            }
            return orders;
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
    public List<Order> readByStatusAndDriverId(OrderStatus orderStatus, Long driverId) throws DaoException {
        String sql = "SELECT `id`,`date`, `departure_point`, `arrival_point`, `vehicle_type`, `status`, `users_id` , `drivers_id` FROM `orders` WHERE `status` = ? AND `drivers_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setInt(1, orderStatus.ordinal());
            statement.setLong(2, driverId);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDate(new Date(resultSet.getTimestamp("date").getTime()));
                order.setDeparturePoint(resultSet.getString("departure_point"));
                order.setArrivalPoint(resultSet.getString("arrival_point"));
                order.setVehicleType(VehicleType.values()[resultSet.getInt("vehicle_type")]);
                order.setStatus(orderStatus);
                if (!resultSet.wasNull()) {
                    order.setUser(new User());
                    order.getUser().setId(resultSet.getLong("users_id"));
                }
                if (!resultSet.wasNull()) {
                    order.setDriver(new Driver());
                    order.getDriver().setId(driverId);
                }
                orders.add(order);
            }
            return orders;
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
    public Order read(Long id) throws DaoException {
        String sql = "SELECT `id`, `date`, `departure_point`, `arrival_point`, `vehicle_type`, `status`, `users_id`, `drivers_id` FROM `orders` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setLong(1, id);
            resultSet = statement.executeQuery();
            Order order = null;
            if (resultSet.next()) {
                order = new Order();
                order.setId(id);
                order.setDate(new Date(resultSet.getTimestamp("date").getTime()));
                order.setDeparturePoint(resultSet.getString("departure_point"));
                order.setArrivalPoint(resultSet.getString("arrival_point"));
                order.setVehicleType(VehicleType.values()[resultSet.getInt("vehicle_type")]);
                order.setStatus(OrderStatus.values()[resultSet.getInt("status")]);
                if (!resultSet.wasNull()) {
                    order.setUser(new User());
                    order.getUser().setId(resultSet.getLong("users_id"));
                }
                if (!resultSet.wasNull()) {
                    order.setDriver(new Driver());
                    order.getDriver().setId(resultSet.getLong("drivers_id"));
                }
            }
            return order;
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
    public Long create(Order order) throws DaoException {
        String sql = "INSERT INTO `orders` (`date`, `departure_point`, `arrival_point`, `vehicle_type`, `status`, `users_id`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, new java.sql.Date(order.getDate().getTime()));
            statement.setString(2, order.getDeparturePoint());
            statement.setString(3, order.getArrivalPoint());
            statement.setInt(4, order.getVehicleType().ordinal());
            statement.setInt(5, order.getStatus().ordinal());
            statement.setLong(6, order.getUser().getId());
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
    public void update(Order order) throws DaoException {
        String sql = "UPDATE `orders` SET `date` = ?, `departure_point` = ?, `arrival_point` = ?, `vehicle_type` = ?, `status` = ?, `users_id` = ?, `drivers_id` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(order.getDate().getTime()));
            statement.setString(2, order.getDeparturePoint());
            statement.setString(3, order.getArrivalPoint());
            statement.setInt(4, order.getVehicleType().ordinal());
            statement.setInt(5, order.getStatus().ordinal());
            statement.setLong(6, order.getUser().getId());
            if (order.getDriver() != null) {
                statement.setLong(7, order.getDriver().getId());
            } else {
                statement.setNull(7, Types.BIGINT);
            }
            statement.setLong(8, order.getId());
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
        String sql = "DELETE FROM `orders` WHERE `id` = ?";
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
