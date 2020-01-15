package pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPool {
    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;
    private int maxSize;
    private int validationTimout;

    private Queue<Connection> freeConnections = new ConcurrentLinkedQueue<>();
    private Set<Connection> usedConnections = new ConcurrentSkipListSet<>();

    private ConnectionPool() {}

    public Connection getConnection() throws PoolException {
        Connection connection = null;
        while(connection == null) {
            try {
                connection = freeConnections.poll();
                if(connection != null) {
                    if(!connection.isValid(validationTimout)) {
                        close(connection);
                        connection = null;
                    }
                } else if(maxSize == 0 || usedConnections.size() < maxSize) {
                    connection = establishConnection();
                } else {
                    throw new PoolException("maxSize exceeded");
                }
            } catch(SQLException e) {
                throw new PoolException(e);
            }
        }
        usedConnections.add(connection);
        return connection;
    }

    void freeConnection(Connection connection) throws SQLException {
        try {
            usedConnections.remove(connection);
            connection.clearWarnings();
            connection.setAutoCommit(true);
            freeConnections.add(connection);
        } catch(SQLException e) {
            close(connection);
            throw e;
        }
    }

    public void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword, int minSize, int maxSize, int validationTimeout) throws PoolException {
        try {
            if(minSize <= maxSize) {
                Class.forName(jdbcDriver);
                this.jdbcUrl = jdbcUrl;
                this.jdbcUser = jdbcUser;
                this.jdbcPassword = jdbcPassword;
                for(int i = 0; i < minSize; i++) {
                    freeConnections.add(establishConnection());
                }
                this.maxSize = maxSize;
                this.validationTimout = validationTimeout;
            } else {
                throw new PoolException("minSize is to be less or equal maxSize");
            }
        } catch(ClassNotFoundException | SQLException e) {
            throw new PoolException(e);
        }
    }

    public void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword, int minSize, int maxSize) throws PoolException {
        init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword, minSize, maxSize, 0);
    }

    public void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword, int size) throws PoolException {
        init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword, 0, size, 0);
    }

    public  void init(String jdbcDriver, String jdbcUrl, String jdbcUser, String jdbcPassword) throws PoolException {
        init(jdbcDriver, jdbcUrl, jdbcUser, jdbcPassword, 0, 0, 0);
    }

    public void destroy() {
        synchronized(usedConnections) {
            synchronized(freeConnections) {
                usedConnections.addAll(freeConnections);
                freeConnections.clear();
                for(Connection connection : usedConnections) {
                    close(connection);
                }
                usedConnections.clear();
                closer.shutdown();
            }
        }
    }

    private Connection establishConnection() throws SQLException {
        return new ConnectionWrapper(DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword));
    }

    private void close(Connection connection) {
        closer.execute(() -> {
            synchronized(connection) {
                try { connection.rollback(); } catch(SQLException e) {}
                try { ((ConnectionWrapper)connection).getConnection().close(); } catch(SQLException e) {}
            }
        });
    }

    private static ConnectionPool instance;
    private static ExecutorService closer = Executors.newSingleThreadExecutor();

    public static ConnectionPool getInstance() {
        if(instance == null) {
            synchronized(ConnectionPool.class) {
                if(instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }
}
