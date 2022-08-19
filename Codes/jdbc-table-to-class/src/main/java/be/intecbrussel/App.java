package be.intecbrussel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        String SEPARATOR = "###################################################################";

        // create an empty list of users
        List<User> userList = new ArrayList<>();

        Connection connection = DriverUtils.connect("localhost", "root", "toor", "demodb", true);

        System.out.println("Connection object reference is: " + connection);

        Statement selectStatement = null;

        try {
            selectStatement = connection.createStatement();

            System.out.println("Statement object reference is: " + selectStatement);

            ResultSet resultSet = selectStatement.executeQuery("SELECT * FROM users_jdbc");

            System.out.println("ResultSet object reference is: " + resultSet);


            int counter = 0;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String familyName = resultSet.getString("family_name");
                int age = resultSet.getInt("age");

                System.out.println("Now reading row " + (++counter));

                User user = new User();
                user.setId(id);
                user.setName(name);
                user.setFamilyName(familyName);
                user.setAge(age);

                userList.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Could not insert user: " + e.getMessage());
        }


        System.out.println(SEPARATOR);

        for (User user : userList) {
            System.out.println(user);
        }



    }
}
