package domain;

public enum Role {
    USER("role.user"),
    DRIVER("role.driver"),
    DISPATCHER("role.dispatcher"),
    ADMINISTRATOR("role.admin");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }
}
