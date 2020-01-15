package domain;

import java.util.Date;

public class Order extends Entity {
    private Date date;
    private String departurePoint;
    private String arrivalPoint;
    private VehicleType vehicleType;
    private OrderStatus status;
    private User user;
    private Driver driver;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDeparturePoint() {
        return departurePoint;
    }

    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    public String getArrivalPoint() {
        return arrivalPoint;
    }

    public void setArrivalPoint(String arrivalPoint) {
        this.arrivalPoint = arrivalPoint;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("date=").append(date);
        sb.append(", departurePoint='").append(departurePoint).append('\'');
        sb.append(", arrivalPoint='").append(arrivalPoint).append('\'');
        sb.append(", vehicleType=").append(vehicleType);
        sb.append(", status=").append(status);
        sb.append(", user=").append(user);
        sb.append(", driver=").append(driver);
        sb.append('}');
        return sb.toString();
    }
}
