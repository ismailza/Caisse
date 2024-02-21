package ma.fstm.ilisi.caisse.controlleur;

import ma.fstm.ilisi.caisse.metier.bo.Article;
import ma.fstm.ilisi.caisse.metier.bo.Vente;
import ma.fstm.ilisi.caisse.metier.dao.DAOArticle;
import ma.fstm.ilisi.caisse.metier.dao.DAOVente;

import java.util.Collection;

public class Magasin {
    private static Magasin magasin;
    private Collection<Article> articles;

    private Magasin() {
        articles = new DAOArticle().retreive();
    }

    public static Magasin getInstance() {
        if (magasin == null)
            magasin = new Magasin();
        return magasin;
    }

    public Article chercherArticle(String reference) {
        for (Article article : articles)
            if (article.getReference().equals(reference))
                return article;
        return null;
    }

    public boolean historiser(Vente vente) {
        return new DAOVente().create(vente);
    }
}
