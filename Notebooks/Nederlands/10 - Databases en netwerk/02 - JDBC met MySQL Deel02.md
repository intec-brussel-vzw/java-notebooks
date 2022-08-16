---
created: 16-08-222022
tags: []
source: https://github.com/yilmazchef/repo
author: Yilmaz Mustafa
---

# 7.2 JDBC-instructieobjecten gebruiken om SQL uit te voeren

> ## Excerpt
> 7.2 JDBC-objecten gebruiken om SQL uit te voerenStatement

---
## 7.2 JDBC-objecten gebruiken om SQL uit te voeren`Statement`

`Statement` met objecten kunt u basis SQL-query's uitvoeren en de resultaten ophalen via de klasse, die later wordt beschreven. `ResultSet`

Als u een instantie wilt maken, roept u de methode aan voor het object dat u hebt opgehaald met een van de eerder beschreven methoden of methoden. `Statement``createStatement()``Connection``DriverManager.getConnection()``DataSource.getConnection()`

Zodra u een instantie hebt, kunt u een [`SELECT-query`](https://dev.mysql.com/doc/refman/8.0/en/select.html) uitvoeren door de methode aan te roepen met de SQL die u wilt gebruiken. `Statement``executeQuery(String)`

Gebruik de methode om gegevens in de database bij te werken. Deze methode retourneert het aantal rijen dat overeenkomt met de update-instructie, niet het aantal rijen dat is gewijzigd. `executeUpdate(String SQL)`

Als u van tevoren niet weet of de SQL-instructie een [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) of een [`UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html)/[`INSERT`](https://dev.mysql.com/doc/refman/8.0/en/insert.html) zal zijn, kunt u de methode gebruiken. Deze methode retourneert true als de SQL-query een [`SELECT`](https://dev.mysql.com/doc/refman/8.0/en/select.html) was, of false als het een [`UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html)\-, [`INSERT-`](https://dev.mysql.com/doc/refman/8.0/en/insert.html) of [`DELETE-instructie`](https://dev.mysql.com/doc/refman/8.0/en/delete.html) was. Als de instructie een [`SELECT-query`](https://dev.mysql.com/doc/refman/8.0/en/select.html) was, kunt u de resultaten ophalen door de methode aan te roepen. Als de instructie een [`INSTRUCTIE UPDATE`](https://dev.mysql.com/doc/refman/8.0/en/update.html), [`INSERT`](https://dev.mysql.com/doc/refman/8.0/en/insert.html) of [`DELETE`](https://dev.mysql.com/doc/refman/8.0/en/delete.html) was, kunt u het aantal betreffende rijen ophalen door de instantie aan te roepen. `execute(String SQL)``getResultSet()``getUpdateCount()``Statement`

**Voorbeeld 7.2 Connector/J: java.sql.Statement gebruiken om een `SELECT-query` uit te voeren**

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
