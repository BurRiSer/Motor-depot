package controller;

import pool.ConnectionPool;
import pool.PoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationStartListener implements ServletContextListener {

    public static final Logger logger = LogManager.getLogger(ApplicationStartListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            ConnectionPool.getInstance().init("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost/mydb?useUnicode=true&characterEncoding=UTF-8",
                    "root", "mysql");
        } catch (PoolException e) {
            logger.fatal("Can't init ConnectionPool", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().destroy();
    }
}
