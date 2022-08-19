package be.intecbrussel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.Objects;

public class DriverUtils {

    /**
     * @param host     if the value is left null, then the default value as
     *                 'localhost' is applied.
     * @param username if the username is left null, then the default value as
     *                 'root' is applied
     * @param password if the password is left null, then the default value as
     *                 'toor' is applied.
     * @return
     */
    public static Connection connect(String host,
                                      String username, String password,
                                      String database, boolean autoCreateDB) {

        if (Objects.isNull(host)) {
            host = "localhost";
        }

        if (Objects.isNull(username)) {
            username = "root";
        }

        if (Objects.isNull(password)) {
            password = "toor";
        }

        String SEPERATOR = "###################################################################";

        System.out.println(SEPERATOR);
        System.out.println("Loading the driver...");

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            var foundMySQLDriver = Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver loaded: " + foundMySQLDriver);

        } catch (ClassNotFoundException ex) {
            System.err.println(
                    "One of the driver classes was not found: " + ex.getMessage());
        }

        System.out.println("Driver loading stage complete.");
        System.out.println(SEPERATOR);


        System.out.println(SEPERATOR);
        String mysqlConnectionUrl;

        if (!Objects.isNull(database)) {
            mysqlConnectionUrl = MessageFormat.format("jdbc:mysql://localhost:3306/{0}", database);
        } else {
            mysqlConnectionUrl = "jdbc:mysql://localhost:3306/demodb";
        }

        System.out.println("Connecting to database URL: " + mysqlConnectionUrl);

        Connection connection = null;

        try {
            // Create a connection to the database
            // "jdbc:database_vendor://database_server_host:port_number/database_name"
            connection = DriverManager.getConnection(mysqlConnectionUrl, username, password);
            System.out.println("Connection: " + connection);

        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }

        System.out.println("Connected to database " + mysqlConnectionUrl);
        System.out.println(SEPERATOR);

        return connection;
    }

    public static void disconnect(Connection connection) {
        if (Objects.isNull(connection)) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
