package ma.fstm.ilisi.caisse.metier.bo;

public class LigneVente {
    private Vente vente;
    private Article article;
    private Float prix_unitaire;
    private int quantite;
    private float subTotal;

    public LigneVente(Vente vente, Article article, int quantite) {
        this.vente = vente;
        this.article = article;
        this.quantite = quantite;
        this.prix_unitaire = article.getPrix();
        this.subTotal = quantite * prix_unitaire;
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
        this.subTotal = quantite * prix_unitaire;
    }

    public int getIdVente() {
        return this.vente.getId();
    }

    public Float getPrix_unitaire() {
        return prix_unitaire;
    }

    public boolean check(Article article) {
        return this.article.equals(article);
    }

    public float getSubTotal() {
        return subTotal;
    }
}
