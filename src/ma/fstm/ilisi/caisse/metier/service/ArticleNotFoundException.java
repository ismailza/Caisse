package ma.fstm.ilisi.caisse.metier.service;

public class ArticleNotFoundException extends Exception {

    public ArticleNotFoundException() {
        super("Article non trouvé!");
    }
}
