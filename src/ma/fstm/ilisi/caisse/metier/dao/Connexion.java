package ma.fstm.ilisi.caisse.metier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static Connexion connexion;
    private static Connection connection;
    private Connexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:caisse", "caisse", "1234");
        } catch (ClassNotFoundException e) {
            System.err.println("Chargement de driver échoué: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Url, login ou mot de passe incorrect: " + e.getMessage());
        }
    };

    public static Connexion getInstance() {
        if (connexion == null)
            connexion = new Connexion();
        return connexion;
    }

    public Connection getConnexion() {
        return connection;
    }
}
