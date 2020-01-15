package pool;

public class PoolException extends Exception {
    public PoolException(String message) {
        super(message);
    }

    public PoolException(Throwable cause) {
        super(cause);
    }
}
