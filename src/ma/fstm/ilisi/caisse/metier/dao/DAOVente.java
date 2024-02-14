package ma.fstm.ilisi.caisse.metier.dao;

import ma.fstm.ilisi.caisse.metier.bo.LigneVente;
import ma.fstm.ilisi.caisse.metier.bo.Vente;

import java.sql.*;
import java.util.Collection;
import java.util.Iterator;

public class DAOVente implements IDAO<Vente> {

    @Override
    public boolean create(Vente vente) {
        try {
            ResultSet resultSet = Connexion.getInstance().getConnexion().createStatement().executeQuery("SELECT vente_seq.NEXTVAL FROM dual");
            if (resultSet.next()) {
                vente.setId(resultSet.getInt(1));
                PreparedStatement preparedStatement = Connexion.getInstance().getConnexion().prepareStatement("INSERT INTO ventes VALUES (?, ?)");
                preparedStatement.clearParameters();
                preparedStatement.setInt(1, vente.getId());
                preparedStatement.setTimestamp(2, vente.getDatetime());
                preparedStatement.executeUpdate();

                Iterator<LigneVente> ligneVenteIterator = vente.getLigneVentes();
                DAOLigneVente daoLigneVente = new DAOLigneVente();
                while(ligneVenteIterator.hasNext()) {
                    daoLigneVente.create(ligneVenteIterator.next());
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Collection<Vente> retreive() {
        return null;
    }

    @Override
    public boolean update(Vente vente) {
        return false;
    }

    @Override
    public boolean delete(Vente vente) {
        try {
            PreparedStatement preparedStatement = Connexion.getInstance().getConnexion().prepareStatement("DELETE FROM ventes WHERE id_vente = ?");
            preparedStatement.clearParameters();
            preparedStatement.setInt(1, vente.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
