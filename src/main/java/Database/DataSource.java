/*
 * User Responsible: Hasbiya Yusuf
 * Course: CST8288
 * Maker Space Final Project
 *
 * Description:
 * This class implements the Singleton pattern to provide database
 * connections for DAO classes. Database configuration is loaded from
 * database.properties to separate connection settings from source code.
 */

package Database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides a Singleton JDBC database connection.
 *
 * @author Hasbiya Yusuf
 * @version 1.0
 */
public class DataSource {

    private static final String PROPERTIES_FILE = "/database.properties";

    private static volatile DataSource instance;

    private Connection connection;

    private String url;
    private String username;
    private String password;

    /**
     * Creates the DataSource and loads database configuration.
     */
    private DataSource() {
        loadProperties();
    }

    /**
     * Returns the single DataSource instance.
     *
     * @return DataSource instance
     */
    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    /**
     * Loads database connection settings from database.properties.
     */
    private void loadProperties() {
        Properties properties = new Properties();

        try (InputStream input = DataSource.class.getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                throw new RuntimeException("Could not find " + PROPERTIES_FILE);
            }
            properties.load(input);
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

        } catch (IOException e) {
            throw new RuntimeException("Failed to load database properties.", e);
        }
    }

    /**
     * Returns an active database connection.
     *
     * @return JDBC connection
     * @throws SQLException if connection fails
     */
    public synchronized Connection getConnection() throws SQLException {

        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC driver not found.", e);
            }
            connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        }
        return connection;
    }
}