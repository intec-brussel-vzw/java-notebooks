package be.intecbrussel;

import com.mysql.cj.conf.ConnectionPropertiesTransform;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.jdbc.ConnectionImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class LoadDriver {
    public static void main(String[] args) {

        String SEPERATOR = "###################################################################";

        System.out.println(SEPERATOR);
        System.out.println("Loading the driver...");

        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            var foundMySQLDriver = Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Driver loaded: " + foundMySQLDriver);

        } catch (ClassNotFoundException ex) {
            System.out.println(
                    "One of the driver classes was not found: " + ex.getMessage());
        }

        System.out.println("Driver loading stage complete.");
        System.out.println(SEPERATOR);


        System.out.println(SEPERATOR);
        String mysqlConnectionUrl = "jdbc:mysql://localhost:3306/demodb";
        String username = "root";
        String password = "toor";
        System.out.println("Connecting to database URL: " + mysqlConnectionUrl);

        try {
            // Create a connection to the database
            // "jdbc:database_vendor://database_server_host:port_number/database_name"
            Connection connection = DriverManager.getConnection(mysqlConnectionUrl, username, password);
            System.out.println("Connection: " + connection);

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        System.out.println("Connected to database " + mysqlConnectionUrl);
        System.out.println(SEPERATOR);

    }
}