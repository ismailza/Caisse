package ma.fstm.ilisi.caisse.metier.bo;

public class Article {
    private String reference;
    private String designation;
    private float prix;

    public Article(String reference, String designation, float prix) {
        this.reference = reference;
        this.designation = designation;
        this.prix = prix;
    }

    public String getReference() {
        return reference;
    }

    public String getDesignation() {
        return designation;
    }

    public float getPrix() {
        return prix;
    }
}
