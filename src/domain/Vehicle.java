package domain;

public class Vehicle extends Entity {
    private String model;
    private Boolean serviceability;
    private VehicleType type;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getServiceability() {
        return serviceability;
    }

    public void setServiceability(Boolean serviceability) {
        this.serviceability = serviceability;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", serviceability=" + serviceability +
                ", type=" + type +
                "} " + super.toString();
    }
}
