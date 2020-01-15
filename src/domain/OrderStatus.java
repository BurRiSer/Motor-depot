package domain;

public enum  OrderStatus {
    NOT_SERVED("status.not_served"),
    ACCEPTED("status.accepted"),
    IN_PROCESS("status.in_process"),
    COMPLETED("status.completed");

    private String name;

    private OrderStatus(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
