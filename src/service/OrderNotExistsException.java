package service;

public class OrderNotExistsException extends ServiceException {
    private Long id;

    public OrderNotExistsException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}