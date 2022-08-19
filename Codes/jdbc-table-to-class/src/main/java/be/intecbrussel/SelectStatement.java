package be.intecbrussel;

import java.sql.*;
import java.text.MessageFormat;

public class SelectStatement {

    private static final String SEPARATOR = "###################################################################";

    public static ResultSet from(Connection connection, String name) throws SQLException {


        ResultSet resultSet = null;
        try {

            System.out.println("Creating a statement...");
            Statement statement = connection.createStatement();
            System.out.println("Statement created.");

            System.out.println(SEPARATOR);

            System.out.println("Executing query...");
            String selectQuery = MessageFormat.format("SELECT * FROM {0}", name);
            resultSet = statement.executeQuery(selectQuery);
            System.out.println("Query executed.");

            System.out.println(SEPARATOR);

            System.out.println("Creating a result set...");
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println("Result set created.");


            System.out.println(SEPARATOR);

            System.out.println("Closing result set...");
            resultSet.close();
            System.out.println("Result set closed.");

            System.out.println(SEPARATOR);
            System.out.println("Closing statement...");
            statement.close();
            System.out.println("Statement closed.");

            System.out.println(SEPARATOR);
            System.out.println("Closing connection...");
            connection.close();
            System.out.println("Connection closed.");
            System.out.println(SEPARATOR);


        } catch (SQLException e) {
            System.err.println("Could not insert user: " + e.getMessage());
        }

        System.out.println(SEPARATOR);

        return resultSet;
    }


}
