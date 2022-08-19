package be.intecbrussel;

import static java.lang.System.err;
import static java.lang.System.out;

import java.sql.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class CreateStatement {

    public static void table(Connection connection, String name)  {

        final String SEPARATOR = "###################################################################";

        out.println(SEPARATOR);

        String createUserTableQuery = MessageFormat.format(
                "CREATE TABLE IF NOT EXISTS {0} (id INT AUTO_INCREMENT PRIMARY KEY",
                name);

        Statement createTableStatement = null;

        try {
            createTableStatement = connection.createStatement();
            boolean isSelectedStatement = createTableStatement.execute(createUserTableQuery);
            out.println("Statement is SELECT: " + isSelectedStatement);

        } catch (SQLException e) {
            err.println("Could not create statement: " + e.getMessage());
        }

        boolean createTableResult = false;
        try {
            createTableResult = !createTableStatement.execute(createUserTableQuery);
        } catch (SQLException e) {
            err.println(e.getMessage());
        }

        out.println(SEPARATOR);

        if (!createTableResult) {
            out.println("Table " + name + " created.");
        } else {
            out.println("Table " + name + " already exists.");
        }

        try {

            System.out.println("Creating a statement...");
            Statement statement = connection.createStatement();
            System.out.println("Statement created.");

            System.out.println(SEPARATOR);

            System.out.println("Executing query...");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_jdbc");
            System.out.println("Query executed.");

            System.out.println(SEPARATOR);

            System.out.println("Creating a result set...");
            ResultSetMetaData metaData = resultSet.getMetaData();
            System.out.println("Result set created.");

            System.out.println(SEPARATOR);

            System.out.println("Printing result set metadata...");

            while (resultSet.next()) {
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    System.out.print(resultSet.getString(i) + " ");
                }
                System.out.println();
            }

            System.out.println(SEPARATOR);

            out.println("Printing result set contents...");

            while(resultSet.next()) {

                int idValue = resultSet.getInt("id");
                String nameValue = resultSet.getString("name");
                String familyNameValue = resultSet.getString("family_name");
                int ageValue = resultSet.getInt("age");

                System.out.println("id: " + idValue);
                System.out.println("name: " + nameValue);
                System.out.println("family_name: " + familyNameValue);
                System.out.println("age: " + ageValue);
            }

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

    }

}
