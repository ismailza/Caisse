package ma.fstm.ilisi.caisse.metier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    public static Connection getConnexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:caisse", "caisse", "1234");
        } catch (ClassNotFoundException e) {
            System.err.println("Chargement de driver échoué: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.err.println("Url, login ou mot de passe incorrect: " + e.getMessage());
            return null;
        }
    }
}
