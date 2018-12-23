package com.svladiko.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connection on database MySQL
 *
 * @author Vladislav Serhiychuk
 */
public class DBWorker {

    private final String HOST = "jdbc:mysql://localhost:3306/bank";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    private Connection connection;

    /**
     * Through this method we can create a connection
     *
     * @return Connection
     */
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = (Connection) DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
