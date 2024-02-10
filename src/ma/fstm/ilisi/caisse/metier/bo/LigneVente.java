package ma.fstm.ilisi.caisse.metier.bo;

public class LigneVente {
    private Article article;
    private int quantite;
    private float subTotal;

    public LigneVente(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
        this.subTotal = quantite * article.getPrix();
    }

    public String getReference() {
        return article.getReference();
    }
    public String getDesignation() {
        return article.getDesignation();
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        this.subTotal = quantite * article.getPrix();
    }

    public boolean check(Article article) {
        return this.article.equals(article);
    }

    public float getSubTotal() {
        return subTotal;
    }
}
