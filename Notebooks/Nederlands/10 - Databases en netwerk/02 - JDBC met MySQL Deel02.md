---
created: 16-08-222022
tags: []
source: https://github.com/yilmazchef/repo
author: Yilmaz Mustafa
---

# 7.2 Using JDBC Statement Objects to Execute SQL

> ## Excerpt
> Statement objects allow you to execute
        basic SQL queries and retrieve the results through the
        ResultSet class, which is described later.

---
`Statement` objects allow you to execute basic SQL queries and retrieve the results through the `ResultSet` class, which is described later.

To create a `Statement` instance, you call the `createStatement()` method on the `Connection` object you have retrieved using one of the `DriverManager.getConnection()` or `DataSource.getConnection()` methods described earlier.

Once you have a `Statement` instance, you can execute a [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) query by calling the `executeQuery(String)` method with the SQL you want to use.

To update data in the database, use the `executeUpdate(String SQL)` method. This method returns the number of rows matched by the update statement, not the number of rows that were modified.

If you do not know ahead of time whether the SQL statement will be a [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) or an [`UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html)/[`INSERT`](https://dev.mysql.com/doc/refman/8.0/en/insert.html), then you can use the `execute(String SQL)` method. This method will return true if the SQL query was a [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html), or false if it was an [`UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html), [`INSERT`](https://dev.mysql.com/doc/refman/8.0/en/insert.html), or [`DELETE`](https://dev.mysql.com/doc/refman/8.0/en/delete.html) statement. If the statement was a [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) query, you can retrieve the results by calling the `getResultSet()` method. If the statement was an [`UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html), [`INSERT`](https://dev.mysql.com/doc/refman/8.0/en/insert.html), or [`DELETE`](https://dev.mysql.com/doc/refman/8.0/en/delete.html) statement, you can retrieve the affected rows count by calling `getUpdateCount()` on the `Statement` instance.

**Example 7.2 Connector/J: Using java.sql.Statement to execute a `SELECT` query**

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

// assume that conn is an already created JDBC connection (see previous examples)

Statement stmt = null;
ResultSet rs = null;

try {
    stmt = conn.createStatement();
    rs = stmt.executeQuery("SELECT foo FROM bar");

    // or alternatively, if you don't know ahead of time that
    // the query will be a SELECT...

    if (stmt.execute("SELECT foo FROM bar")) {
        rs = stmt.getResultSet();
    }

    // Now do something with the ResultSet ....
}
catch (SQLException ex){
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
finally {
    // it is a good idea to release
    // resources in a finally{} block
    // in reverse-order of their creation
    // if they are no-longer needed

    if (rs != null) {
        try {
            rs.close();
        } catch (SQLException sqlEx) { } // ignore

        rs = null;
    }

    if (stmt != null) {
        try {
            stmt.close();
        } catch (SQLException sqlEx) { } // ignore

        stmt = null;
    }
}
```
