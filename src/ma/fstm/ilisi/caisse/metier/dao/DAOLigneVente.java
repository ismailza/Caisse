package ma.fstm.ilisi.caisse.metier.dao;

import ma.fstm.ilisi.caisse.metier.bo.LigneVente;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class DAOLigneVente implements IDAO<LigneVente> {

    @Override
    public boolean create(LigneVente ldv) {
        try {
            PreparedStatement preparedStatement = Connexion.getInstance().getConnexion().prepareStatement("INSERT INTO lignes_vente (id_vente, reference, prix_unitaire, quantite) VALUES (?, ?, ?, ?)");
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, ldv.getIdVente());
            preparedStatement.setString(2, ldv.getReference());
            preparedStatement.setFloat(3, ldv.getPrix_unitaire());
            preparedStatement.setInt(4, ldv.getQuantite());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Collection<LigneVente> retreive() {
        return null;
    }

    @Override
    public boolean update(LigneVente ldv) {
        return false;
    }

    @Override
    public boolean delete(LigneVente ldv) {
        return false;
    }
}
