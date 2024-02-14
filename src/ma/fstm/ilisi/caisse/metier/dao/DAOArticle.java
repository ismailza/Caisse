package ma.fstm.ilisi.caisse.metier.dao;

import ma.fstm.ilisi.caisse.metier.bo.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

public class DAOArticle implements IDAO<Article> {

    @Override
    public boolean create(Article obj) {
        return false;
    }

    @Override
    public Collection<Article> retreive() {
        Collection<Article> articles = new HashSet<>();
        try {
            ResultSet resultSet = Connexion.getInstance().getConnexion().createStatement().executeQuery("SELECT reference, designation, prix FROM articles");
            while (resultSet.next())
                articles.add(new Article(resultSet.getString("reference"), resultSet.getString("designation"), resultSet.getFloat("prix")));
            return articles;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Article obj) {
        return false;
    }

    @Override
    public boolean delete(Article obj) {
        return false;
    }
}
