package ma.fstm.ilisi.caisse.metier.bo;

import java.sql.Timestamp;
import java.util.*;

public class Vente {
    private int id;
    private Timestamp datetime;
    private ArrayList<LigneVente> ligneVentes;
    private float total;
    private boolean termine;

    public Vente() {
        this.datetime = new Timestamp(System.currentTimeMillis());
        this.ligneVentes = new ArrayList<>();
        this.total = 0;
        this.termine = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public float getTotal() {
        return total;
    }

    public void finirVente() {
        this.termine = true;
    }

    public Iterator<LigneVente> getLigneVentes() {
        return ligneVentes.iterator();
    }

    public void ajouter(Article article, int quantite) {
        for (LigneVente ldv : ligneVentes) {
            if (ldv.check(article)) {
                denombrerQuantite(ldv, ldv.getQuantite() + quantite);
                return;
            }
        }
        LigneVente ligneVente = new LigneVente(this, article, quantite);
        this.ligneVentes.add(ligneVente);
        this.total += ligneVente.getSubTotal();
    }

    public void denombrerQuantite(LigneVente ligneVente, int newQuantite) {
        this.total -= ligneVente.getSubTotal();
        ligneVente.setQuantite(newQuantite);
        this.total += ligneVente.getSubTotal();
    }

    public boolean payerSomme() {
        return termine;
    }

    public LigneVente chercherArticle(String reference) {
        for (LigneVente ldv : ligneVentes) {
            if (ldv.getReference().equals(reference))
                return ldv;
        }
        return null;
    }

    public void annulerArticle(LigneVente ligneVente) {
        total -= ligneVente.getSubTotal();
        ligneVentes.remove(ligneVente);
    }
}
