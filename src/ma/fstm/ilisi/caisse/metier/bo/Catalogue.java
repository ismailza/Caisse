package ma.fstm.ilisi.caisse.metier.bo;

import java.util.Hashtable;

public class Catalogue {
    private Hashtable<String, Article> articles;

    public Catalogue() {
        articles = new Hashtable<>();
        articles.put("123456789", new Article("123456789", "Article 1", 200));
        articles.put("123456780", new Article("123456780", "Article 2", 140));
        articles.put("123456781", new Article("123456780", "Article 3", 19.9f));
    }
    public Article chercherArticle(String reference) {
        return articles.get(reference);
    }
}
