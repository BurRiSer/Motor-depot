package service;

public class VehicleNotExistsException extends ServiceException {
    private Long id;

    public VehicleNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}