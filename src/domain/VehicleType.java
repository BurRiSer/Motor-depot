package domain;

public enum VehicleType {
    SEDAN("type.sedan"),
    WAGON("type.wagon"),
    MINIVAN("type.minivan"),
    MINIBUS("type.minibus");

    private String name;

    private VehicleType(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}

