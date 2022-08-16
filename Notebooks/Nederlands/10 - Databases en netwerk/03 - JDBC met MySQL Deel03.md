---
created: 16-08-222022
tags: []
source: https://github.com/yilmazchef/repo
author: Yilmaz Mustafa
---

# 7.3 JDBC CallableStatements gebruiken om opgeslagen procedures uit te voeren

> ## Excerpt
> 7.3 JDBC gebruiken om opgeslagen procedures uit te voerenCallableStatements

---
## 7.3 JDBC gebruiken om opgeslagen procedures uit te voeren`CallableStatements`

Connector/J implementeert de interface volledig. `java.sql.CallableStatement`

Voor meer informatie over opgeslagen procedures in MySQL raadpleegt u [Opgeslagen routines gebruiken](https://dev.mysql.com/doc/refman/8.0/en/stored-routines.html).

Connector/J stelt opgeslagen procedurefunctionaliteit beschikbaar via de interface van JDBC. `CallableStatement`

In het volgende voorbeeld ziet u een opgeslagen procedure die de waarde van verhoogd met 1 retourneert en de tekenreeks die wordt doorgegeven met behulp van: `inOutParam``inputParam``ResultSet`

**Voorbeeld 7.3 Connector/J: Opgeslagen procedures aanroepen**

```
CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255), \
                        INOUT inOutParam INT)
BEGIN
    DECLARE z INT;
    SET z = inOutParam + 1;
    SET inOutParam = z;

    SELECT inputParam;

    SELECT CONCAT('zyxw', inputParam);
END
```

Ga als volgt te werk om de procedure met Connector/J te gebruiken: `demoSp`

1.  Bereid de oproepbare verklaring voor met behulp van . `Connection.prepareCall()`
    
    U moet de JDBC-escapesyntaxis gebruiken en de haakjes rond de tijdelijke aanduidingen voor de parameter zijn niet optioneel:
    
    **Voorbeeld 7.4 Connector/J: `Connection.prepareCall()` gebruiken**
    
    ```
    import java.sql.CallableStatement;
    
    ...
    
        //
        // Prepare a call to the stored procedure 'demoSp'
        // with two parameters
        //
        // Notice the use of JDBC-escape syntax ({call ...})
        //
    
        CallableStatement cStmt = conn.prepareCall("{call demoSp(?, ?)}");
    
    
    
        cStmt.setString(1, "abcdefg");
    ```
    
      
    
    Notitie
    
    `Connection.prepareCall()` is een dure methode, vanwege het ophalen van metagegevens die het stuurprogramma uitvoert om uitvoerparameters te ondersteunen. Om prestatieredenen kunt u onnodige aanroepen minimaliseren door instanties in uw code opnieuw te gebruiken. `Connection.prepareCall()``CallableStatement`
    
2.  Registreer de uitvoerparameters (indien aanwezig)
    
    Om de waarden van uitvoerparameters (parameters die zijn opgegeven als of wanneer u de opgeslagen procedure hebt gemaakt) op te halen, vereist JDBC dat deze worden opgegeven voordat de instructie wordt uitgevoerd met behulp van de verschillende methoden in de interface: `OUT``INOUT``registerOutputParameter()``CallableStatement`
    
    **Voorbeeld 7.5 Connector/J: uitvoerparameters registreren**
    
    ```
    import java.sql.Types;
    ...
    //
    // Connector/J supports both named and indexed
    // output parameters. You can register output
    // parameters using either method, as well
    // as retrieve output parameters using either
    // method, regardless of what method was
    // used to register them.
    //
    // The following examples show how to use
    // the various methods of registering
    // output parameters (you should of course
    // use only one registration per parameter).
    //
    
    //
    // Registers the second parameter as output, and
    // uses the type 'INTEGER' for values returned from
    // getObject()
    //
    
    cStmt.registerOutParameter(2, Types.INTEGER);
    
    //
    // Registers the named parameter 'inOutParam', and
    // uses the type 'INTEGER' for values returned from
    // getObject()
    //
    
    cStmt.registerOutParameter("inOutParam", Types.INTEGER);
    ...
    ```
    
3.  Set the input parameters (if any exist)
    
    Input and in/out parameters are set as for objects. However, also supports setting parameters by name: `PreparedStatement``CallableStatement`
    
    **Example 7.6 Connector/J: Setting `CallableStatement` input parameters**
    
    ```
    ...
    
        //
        // Set a parameter by index
        //
    
        cStmt.setString(1, "abcdefg");
    
        //
        // Alternatively, set a parameter using
        // the parameter name
        //
    
        cStmt.setString("inputParam", "abcdefg");
    
        //
        // Set the 'in/out' parameter using an index
        //
    
        cStmt.setInt(2, 1);
    
        //
        // Alternatively, set the 'in/out' parameter
        // by name
        //
    
        cStmt.setInt("inOutParam", 1);
    
    ...
    ```
    
4.  Execute the , and retrieve any result sets or output parameters. `CallableStatement`
    
    Although supports calling any of the execute methods (, or ), the most flexible method to call is , as you do not need to know ahead of time if the stored procedure returns result sets: `CallableStatement``Statement``executeUpdate()``executeQuery()``execute()``execute()`
    
    **Example 7.7 Connector/J: Retrieving results and output parameter values**
    
    ```
    ...
    
        boolean hadResults = cStmt.execute();
    
        //
        // Process all returned result sets
        //
    
        while (hadResults) {
            ResultSet rs = cStmt.getResultSet();
    
            // process result set
            ...
    
            hadResults = cStmt.getMoreResults();
        }
    
        //
        // Retrieve output parameters
        //
        // Connector/J supports both index-based and
        // name-based retrieval
        //
    
        int outputValue = cStmt.getInt(2); // index-based
    
        outputValue = cStmt.getInt("inOutParam"); // name-based
    
    ...
    ```
