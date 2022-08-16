---
created: 16-08-222022
tags: []
source: https://github.com/yilmazchef/repo
author: Yilmaz Mustafa
---

# 7.1 Verbinding maken met MySQL via de JDBC DriverManager Interface

> ## Excerpt
> 7.1 Verbinding maken met MySQL via de JDBC-interfaceDriverManager

---
## 7.1 Verbinding maken met MySQL via de JDBC-interface`DriverManager`

Wanneer u JDBC buiten een toepassingsserver gebruikt, beheert de klasse het tot stand brengen van verbindingen. `DriverManager`

Geef op met welke JDBC-stuurprogramma's u verbindingen wilt maken. De eenvoudigste manier om dit te doen is door te gebruiken op de klasse die de interface implementeert. Bij MySQL Connector/J is de naam van deze klasse . Met deze methode kunt u een extern configuratiebestand gebruiken om de naam van de stuurprogrammaklasse en de stuurprogrammaparameters op te geven die moeten worden gebruikt wanneer u verbinding maakt met een database. `DriverManager``Class.forName()``java.sql.Driver``com.mysql.cj.jdbc.Driver`

In het volgende gedeelte van Java-code ziet u hoe u MySQL Connector/J kunt registreren vanaf de methode van uw toepassing. Als u deze code test, leest u eerst de installatiesectie in [hoofdstuk 4, _Connector/J-installatie_](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-installing.html "Chapter 4 Connector/J Installation"), om er zeker van te zijn dat de connector correct is geïnstalleerd en de installatie is ingesteld. Zorg er ook voor dat MySQL is geconfigureerd om externe TCP/IP-verbindingen te accepteren. `main()``CLASSPATH`

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class LoadDriver {
    public static void main(String[] args) {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        }
    }
}
```

Nadat het stuurprogramma is geregistreerd bij de , kunt u een instantie verkrijgen die is verbonden met een bepaalde database door het volgende aan te roepen: `DriverManager``Connection``DriverManager.getConnection()`

**Voorbeeld 7.1 Connector/J: Een verbinding verkrijgen van de `DriverManager`**

Als u dit nog niet hebt gedaan, lees dan het gedeelte van [Paragraaf 7.1, "Verbinding maken met MySQL met behulp van de JDBC `DriverManager` Interface"](https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-usagenotes-connect-drivermanager.html "7.1 Connecting to MySQL Using the JDBC DriverManager Interface") hierboven voordat u met het onderstaande voorbeeld werkt.

In dit voorbeeld ziet u hoe u een instantie kunt verkrijgen van de . Er zijn een paar verschillende handtekeningen voor de methode. Raadpleeg de API-documentatie die bij uw JDK wordt geleverd voor meer specifieke informatie over het gebruik ervan. `Connection``DriverManager``getConnection()`

```
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

Connection conn = null;
...
try {
    conn =
       DriverManager.getConnection("jdbc:mysql://localhost/test?" +
                                   "user=minty&password=greatsqldb");

    // Do something with the Connection

   ...
} catch (SQLException ex) {
    // handle any errors
    System.out.println("SQLException: " + ex.getMessage());
    System.out.println("SQLState: " + ex.getSQLState());
    System.out.println("VendorError: " + ex.getErrorCode());
}
```

Zodra a is vastgesteld, kan het worden gebruikt om objecten te maken en op te halen, evenals metadata over de database op te halen. Dit wordt in de volgende secties uitgelegd. `Connection``Statement``PreparedStatement`

_Voor Connector/J 8.0.24 en hoger:_ Wanneer de gebruiker voor de verbinding niet is gespecificeerd, gebruiken connector/J's implementaties van de verificatieplug-ins standaard de naam van de os-gebruiker die de toepassing uitvoert voor verificatie met de MySQL-server (behalve wanneer de Kerberos-verificatieplug-in wordt gebruikt; zie [Paragraaf 6.12.2, "Verbinding maken met Kerberos"](https://dev.mysql.com/doc/connector-j/8.0/en/connecting-using-kerberos.html "6.12.2 Connecting Using Kerberos") voor meer informatie).

Notitie

Een gebruikersnaam wordt alleen als niet-gespecificeerd beschouwd als aan alle volgende voorwaarden is voldaan:

1.  De methode wordt niet gebruikt. `DriverManager.getConnection(String url, String user, String password)`
    
2.  De eigenschap connection wordt niet gebruikt in bijvoorbeeld de verbindings-URL of elders. `user`
    
3.  De gebruiker wordt niet vermeld in de autoriteit van de verbindings-URL, zoals in , of. `jdbc:mysql://localhost:3306/test` `jdbc:mysql://@localhost:3306/test`
    

Merk op of (1) of (2) niet waar is en een lege tekenreeks wordt doorgegeven, de gebruikersnaam dan een lege tekenreeks is en niet als niet-gespecificeerd wordt beschouwd.
